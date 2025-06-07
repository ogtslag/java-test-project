package com.hmm.test.domain.port;

import com.hmm.test.domain.model.Payment;

import java.util.Optional;

public interface PaymentRepositoryPort {
    Payment save(Payment payment);
    Optional<Payment> findById(String id);
    Optional<Payment> modifyStatus(String id, String status);
}
