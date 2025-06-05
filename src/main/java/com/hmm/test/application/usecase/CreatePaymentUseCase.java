package com.hmm.test.application.usecase;

import com.hmm.test.domain.model.Payment;

public interface CreatePaymentUseCase {
    Payment create(Payment payment);
}
