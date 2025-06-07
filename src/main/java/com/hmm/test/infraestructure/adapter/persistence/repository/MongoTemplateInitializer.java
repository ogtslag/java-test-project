package com.hmm.test.infraestructure.adapter.persistence.repository;

import com.hmm.test.infraestructure.adapter.persistence.entity.StatusDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongoTemplateInitializer implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {

        if (!mongoTemplate.collectionExists(StatusDocument.class)) {
            System.out.println("Initializing user data using MongoTemplate...");
            StatusDocument status1 = StatusDocument.builder().build();
            status1.setStatus("PENDING");
            StatusDocument status2 = StatusDocument.builder().build();
            status2.setStatus("IN_PROCESS");
            StatusDocument status3 = StatusDocument.builder().build();
            status3.setStatus("PROCESSED");

            mongoTemplate.insert(status1);
            mongoTemplate.insert(status2);
            mongoTemplate.insert(status3);
            System.out.println("User data initialized successfully with MongoTemplate!");
        } else {
            System.out.println("User data already exists, skipping initialization with MongoTemplate.");
        }
    }
}
