package com.hmm.test.application.service;

import com.hmm.test.application.usecase.CreateReceiverBalance;
import com.hmm.test.application.usecase.ReadReceiverBalanceUseCase;
import com.hmm.test.application.usecase.UpdateReceiverBalanceUseCase;
import com.hmm.test.domain.model.ReceiverBalance;
import com.hmm.test.domain.port.ReceiverBalanceRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiverBalanceService implements CreateReceiverBalance, ReadReceiverBalanceUseCase, UpdateReceiverBalanceUseCase {
    private final ReceiverBalanceRepositoryPort receiverBalanceRepositoryPort;

    @Override
    public ReceiverBalance create(ReceiverBalance receiverBalance) {
        return receiverBalanceRepositoryPort.create (receiverBalance);
    }

    @Override
    public Optional<ReceiverBalance> findByReceiver(String receiver) {
        return receiverBalanceRepositoryPort.findByReceiver(receiver);
    }

    @Override
    public Optional<ReceiverBalance> update(ReceiverBalance receiverBalance) {
        return receiverBalanceRepositoryPort.update(receiverBalance);
    }
}
