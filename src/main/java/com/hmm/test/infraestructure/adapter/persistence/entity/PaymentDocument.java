package com.hmm.test.infraestructure.adapter.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;


@Document(collection = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDocument {
    @Id
    private String id;

    @Field("concept")
    private String concept;

    @Field("number_products")
    private Integer number_products;

    @Field("sender")
    private String sender;

    @Field("receiver")
    private String receiver;

    private String email_receiver;

    @Field("mount")
    private BigDecimal mount;

    @Field("status")
    private String status;

    @Field("created_date")
    private LocalDate created_date;

    @Field("modified_date")
    private LocalDate modified_date;
}
