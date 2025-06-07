package com.hmm.test.application.usecase;

import com.hmm.test.domain.model.ReceiverBalance;

import java.util.Optional;

public interface ReadReceiverBalanceUseCase {
    Optional<ReceiverBalance> findByReceiver(String receiver);
}
