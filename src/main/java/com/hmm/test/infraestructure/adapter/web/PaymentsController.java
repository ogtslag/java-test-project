package com.hmm.test.infraestructure.adapter.web;

import com.hmm.test.domain.model.Payment;
import com.hmm.test.infraestructure.adapter.persistence.MongoPaymentRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    @Autowired
    private MongoPaymentRepositoryAdapter mongoPaymentRepositoryAdapter;

    @PostMapping("")
    public ResponseEntity<Payment> sendPayment(@RequestBody Payment payment){
        var entity = mongoPaymentRepositoryAdapter.save(payment);
        return  ResponseEntity.ok(entity);
    }

}
