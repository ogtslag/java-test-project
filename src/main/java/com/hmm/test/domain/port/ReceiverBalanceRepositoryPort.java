package com.hmm.test.domain.port;

import com.hmm.test.domain.model.ReceiverBalance;

import java.util.Optional;

public interface ReceiverBalanceRepositoryPort {
    Optional<ReceiverBalance> findByReceiver(String receiver);
    ReceiverBalance create(ReceiverBalance receiverBalance);
    Optional<ReceiverBalance> update(ReceiverBalance receiverBalance);
}
