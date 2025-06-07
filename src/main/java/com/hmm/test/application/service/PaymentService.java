package com.hmm.test.application.service;

import com.hmm.test.application.usecase.CreatePaymentUseCase;
import com.hmm.test.application.usecase.ModifyPaymentStatusUseCase;
import com.hmm.test.application.usecase.ReadPaymentStatusUseCase;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.model.Status;
import com.hmm.test.domain.port.StatusRepositoryPort;
import com.hmm.test.domain.responses.ResponseModel;
import com.hmm.test.domain.port.PaymentRepositoryPort;
import com.hmm.test.infraestructure.adapter.messagebroker.payment.RabbitMQPaymentProducer;
import com.hmm.test.infraestructure.adapter.messagebroker.paymentproccesed.RabbitMQPaymentProcessedProducer;
import com.hmm.test.infraestructure.adapter.messagebroker.paymentupdate.RabbitMQPaymentUpdateProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentService implements CreatePaymentUseCase, ReadPaymentStatusUseCase, ModifyPaymentStatusUseCase {
    private final PaymentRepositoryPort paymentRepositoryPort;
    private final StatusRepositoryPort statusRepositoryPort;
    private final RabbitMQPaymentProducer rabbitMQProducer;
    private final RabbitMQPaymentUpdateProducer rabbitMQPaymentUpdateProducer;
    private final RabbitMQPaymentProcessedProducer rabbitMQPaymentProcessedProducer;

    @Override
    public ResponseModel<Payment> create(Payment payment) {
        var paymentSaved = paymentRepositoryPort.save(payment);
        var response = ResponseModel.<Payment>builder().build();
        response.setDetails(paymentSaved);
        response.setStatusCode(200);
        response.setMessage("Payment created successfully");

        rabbitMQProducer.sendMessagePayment(response.getDetails());

        return response;
    }

    @Override
    public ResponseModel<String> readPaymentStatus(String id) {
       var payment = paymentRepositoryPort.findById(id);
        var response = ResponseModel.<String>builder().build();
        if(payment.isPresent()) {
            response.setDetails(payment.get().getStatus());
            response.setStatusCode(200);
            response.setMessage("Find status payment successfully");
        }
        else {
            response.setStatusCode(404);
            response.setMessage("Payment was not found");
        }
        return response;
    }

    @Override
    public ResponseModel<Payment> modifyStatus(String id, String status) {
        var response = ResponseModel.<Payment>builder().build();

        if(!statusRepositoryPort.findAll().stream().map(Status::getStatus).toList().contains(status)){
            response.setStatusCode(400);
            response.setMessage("Invalid status");
            return  response;
        }

        var paymentStatus = paymentRepositoryPort.findById(id);

        if(paymentStatus.isPresent()){
            if(Objects.equals(paymentStatus.get().getStatus(), status)){
                response.setStatusCode(400);
                response.setMessage("Invalid status: status cant be the same");
                return  response;
            }

            if(Objects.equals(paymentStatus.get().getStatus(), "IN_PROCESS") && Objects.equals(status, "PENDING")){
                response.setStatusCode(400);
                response.setMessage("Invalid status: status cant be changed");
                return  response;
            }

            if(Objects.equals(paymentStatus.get().getStatus(), "PROCESSED") && (Objects.equals(status, "PENDING") || Objects.equals(status, "IN_PROCESS"))){
                response.setStatusCode(400);
                response.setMessage("Invalid status: status cant be changed");
                return  response;
            }
        }

        var payment = paymentRepositoryPort.modifyStatus(id,status);
        if(payment.isPresent()) {
            response.setDetails(payment.get());
            response.setStatusCode(200);
            response.setMessage("Status payment modified successfully");

            rabbitMQPaymentUpdateProducer.sendMessagePaymentUpdate(payment.get());

            if(Objects.equals(status, "PROCESSED")){
                rabbitMQPaymentProcessedProducer.sendMessagePaymentProcessed(payment.get());
            }
        }
        else {
            response.setStatusCode(404);
            response.setMessage("Payment was not found");
        }

        return response;
    }
}
