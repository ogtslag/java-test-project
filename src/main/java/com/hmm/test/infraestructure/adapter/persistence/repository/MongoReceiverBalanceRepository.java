package com.hmm.test.infraestructure.adapter.persistence.repository;

import com.hmm.test.infraestructure.adapter.persistence.entity.ReceiverBalanceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoReceiverBalanceRepository extends MongoRepository<ReceiverBalanceDocument, String> {
    Optional<ReceiverBalanceDocument> findByReceiver(String receiver);
}
