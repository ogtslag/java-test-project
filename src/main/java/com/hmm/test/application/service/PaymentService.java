package com.hmm.test.application.service;

import com.hmm.test.application.usecase.CreatePaymentUseCase;
import com.hmm.test.application.usecase.ModifyPaymentStatusUseCase;
import com.hmm.test.application.usecase.ReadPaymentStatusUseCase;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.model.Status;
import com.hmm.test.domain.port.StatusRepositoryPort;
import com.hmm.test.domain.responses.ResponseModel;
import com.hmm.test.domain.port.PaymentRepositoryPort;
import com.hmm.test.infraestructure.adapter.persistence.messagebroker.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements CreatePaymentUseCase, ReadPaymentStatusUseCase, ModifyPaymentStatusUseCase {
    private final PaymentRepositoryPort paymentRepositoryPort;
    private final StatusRepositoryPort statusRepositoryPort;
    private final RabbitMQProducer rabbitMQProducer;

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

        var payment = paymentRepositoryPort.modifyStatus(id,status);
        if(payment.isPresent()) {
            response.setDetails(payment.get());
            response.setStatusCode(200);
            response.setMessage("Status payment modified successfully");
        }
        else {
            response.setStatusCode(404);
            response.setMessage("Payment was not found");
        }

        return response;
    }
}
