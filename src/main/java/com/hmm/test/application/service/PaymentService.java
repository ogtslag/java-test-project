package com.hmm.test.application.service;

import com.hmm.test.application.usecase.CreatePaymentUseCase;
import com.hmm.test.application.usecase.ModifyPaymentStatusUseCase;
import com.hmm.test.application.usecase.ReadPaymentStatusUseCase;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.responses.ResponseModel;
import com.hmm.test.domain.port.PaymentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements CreatePaymentUseCase, ReadPaymentStatusUseCase, ModifyPaymentStatusUseCase {
    private final PaymentRepositoryPort paymentRepositoryPort;

    @Override
    public ResponseModel<Payment> create(Payment payment) {
        var paymentSaved = paymentRepositoryPort.save(payment);
        var response = ResponseModel.<Payment>builder().build();
        response.setDetails(paymentSaved);
        response.setStatusCode(200);
        response.setMessage("Payment created successfully");
        return response;
    }

    @Override
    public ResponseModel<String> readPaymentStatus(String id) {
       var payment = paymentRepositoryPort.findById(id);
        var response = ResponseModel.<String>builder().build();
        response.setDetails(payment.getStatus());
        response.setStatusCode(200);
        response.setMessage("Find status payment successfully");
        return response;
    }

    @Override
    public ResponseModel<Payment> modifyStatus(String id, String status) {
        var payment = paymentRepositoryPort.modifyStatus(id,status);
        var response = ResponseModel.<Payment>builder().build();
        response.setDetails(payment);
        response.setStatusCode(200);
        response.setMessage("Status payment modified successfully");
        return response;
    }
}
