package com.hmm.test.infraestructure.adapter.persistence.repository;

import com.hmm.test.infraestructure.adapter.persistence.entity.PaymentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MongoPaymentRepository extends MongoRepository<PaymentDocument, String>{
    PaymentDocument findBySender(String sender);
}
