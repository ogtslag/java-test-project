package com.hmm.test.infraestructure.adapter.persistence.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDocument {
    @Id
    public String id;

    public String concept;

    public Integer products;

    public String sender;

    public  String receiver;
}
