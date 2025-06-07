package com.hmm.test.infraestructure.adapter.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "receiver_balances")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReceiverBalanceDocument {
    @Id
    private String id;
    private String receiver;
    private BigDecimal amount;
}
