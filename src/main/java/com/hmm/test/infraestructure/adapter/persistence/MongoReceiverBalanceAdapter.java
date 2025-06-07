package com.hmm.test.infraestructure.adapter.persistence;

import com.hmm.test.domain.model.ReceiverBalance;
import com.hmm.test.domain.port.ReceiverBalanceRepositoryPort;
import com.hmm.test.infraestructure.adapter.persistence.entity.PaymentDocument;
import com.hmm.test.infraestructure.adapter.persistence.entity.ReceiverBalanceDocument;
import com.hmm.test.infraestructure.adapter.persistence.repository.MongoReceiverBalanceRepository;
import com.hmm.test.infraestructure.adapter.persistence.repository.MongoStatusRepository;
import com.hmm.test.infraestructure.util.ReceiverBalanceMapper;
import com.hmm.test.infraestructure.util.StatusMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MongoReceiverBalanceAdapter implements ReceiverBalanceRepositoryPort {
    private final MongoReceiverBalanceRepository mongoReceiverBalanceRepository;
    private final ReceiverBalanceMapper receiverBalanceMapper;

    @Override
    public Optional<ReceiverBalance> findByReceiver(String receiver) {
        Optional<ReceiverBalanceDocument> document = mongoReceiverBalanceRepository.findByReceiver(receiver);
        return document.map(receiverBalanceMapper::toModel);
    }

    @Override
    public ReceiverBalance create(ReceiverBalance receiverBalance) {
        ReceiverBalanceDocument document = receiverBalanceMapper.toDocument(receiverBalance);
        System.out.println(document.toString());
        ReceiverBalanceDocument savedDocument = mongoReceiverBalanceRepository.save(document);
        return receiverBalanceMapper.toModel(savedDocument);
    }

    @Override
    public Optional<ReceiverBalance> update(ReceiverBalance receiverBalance) {
        var balance = mongoReceiverBalanceRepository.findById(receiverBalance.getId());
        if(balance.isPresent()){
            balance.get().setAmount(balance.get().getAmount().add(receiverBalance.getAmount()));
            mongoReceiverBalanceRepository.save(balance.get());
            return Optional.of( receiverBalanceMapper.toModel(balance.get()));
        }
        return Optional.empty();
    }
}
