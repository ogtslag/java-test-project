package com.hmm.test.application.usecase;

import com.hmm.test.domain.responses.ResponseModel;

public interface ReadPaymentStatusUseCase {
    ResponseModel<String> readPaymentStatus(String id);
}
