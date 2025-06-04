package com.hmm.test.domain.port;

import com.hmm.test.domain.model.Payment;

public interface PaymentRepository {
    Payment save(Payment payment);
}
