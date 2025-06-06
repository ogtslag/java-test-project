package com.hmm.test.domain.port;

import com.hmm.test.domain.model.Payment;

public interface PaymentRepositoryPort {
    Payment save(Payment payment);
    Payment findById(String id);
    Payment modifyStatus(String id, String status);
}
