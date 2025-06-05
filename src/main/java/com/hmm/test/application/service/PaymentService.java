package com.hmm.test.application.service;

import com.hmm.test.application.usecase.CreatePaymentUseCase;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.port.PaymentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements CreatePaymentUseCase {
    private final PaymentRepositoryPort paymentRepositoryPort;

    @Override
    public Payment create(Payment payment) {
        return  paymentRepositoryPort.save(payment);
    }
}
