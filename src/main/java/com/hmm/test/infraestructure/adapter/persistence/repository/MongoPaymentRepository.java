package com.hmm.test.infraestructure.adapter.persistence.repository;

import com.hmm.test.infraestructure.adapter.persistence.entity.PaymentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MongoPaymentRepository extends MongoRepository<PaymentDocument, UUID>{
    PaymentDocument findBySender(String sender);
}
