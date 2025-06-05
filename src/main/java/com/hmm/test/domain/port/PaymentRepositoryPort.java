package com.hmm.test.domain.port;

import com.hmm.test.domain.model.Payment;

public interface PaymentRepositoryPort {
    Payment save(Payment payment);
}
