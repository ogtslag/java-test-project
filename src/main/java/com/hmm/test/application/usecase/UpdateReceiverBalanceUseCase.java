package com.hmm.test.application.usecase;

import com.hmm.test.domain.model.ReceiverBalance;

import java.util.Optional;

public interface UpdateReceiverBalanceUseCase {
    Optional<ReceiverBalance> update(ReceiverBalance receiverBalance);
}
