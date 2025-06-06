package com.hmm.test.infraestructure.config;

import com.mongodb.MongoCommandException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.ValidationOptions;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
@RequiredArgsConstructor
public class MongoSchemaConfig {
//    private final MongoTemplate mongoTemplate;
//    @Bean
//    public ApplicationRunner setupMongoSchemaValidation(){
//        return args -> {
//            MongoDatabase db = mongoTemplate.getDb();
//
//            String collectionName = "payments";
//
//            db.getCollection(collectionName)
//                    .drop();
//
//            String schema = Files.readString(Paths.get("schema.json"));
//            Document schemaValidation = Document.parse(schema);
//
//            try {
//                ValidationOptions validationOptions = new ValidationOptions().validator(schemaValidation);
//                CreateCollectionOptions createOptions = new CreateCollectionOptions().validationOptions(validationOptions);
//
//
//                db.createCollection(collectionName, createOptions);
//                System.out.println("collection '" + collectionName + "' created.");
//            } catch (MongoCommandException e) {
//                if (e.getErrorCode() == 48) {
//                    db.runCommand(new Document("collMod", collectionName).append("validator", schemaValidation));
//                    System.out.println("Validation schema refreshed: " + collectionName);
//                } else {
//                    throw e;
//                }
//            }
//        };
//    }
}
