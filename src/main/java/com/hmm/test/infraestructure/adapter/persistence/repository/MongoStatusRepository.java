package com.hmm.test.infraestructure.adapter.persistence.repository;

import com.hmm.test.infraestructure.adapter.persistence.entity.StatusDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoStatusRepository extends MongoRepository<StatusDocument, String> {
}
