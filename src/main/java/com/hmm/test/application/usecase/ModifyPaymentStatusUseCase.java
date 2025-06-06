package com.hmm.test.application.usecase;

import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.responses.ResponseModel;

public interface ModifyPaymentStatusUseCase {
    ResponseModel<Payment> modifyStatus(String id, String status);
}
