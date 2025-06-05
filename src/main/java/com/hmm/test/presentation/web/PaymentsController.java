package com.hmm.test.presentation.web;

import com.hmm.test.application.usecase.CreatePaymentUseCase;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.port.PaymentRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final CreatePaymentUseCase createPaymentUseCase;

    @PostMapping("")
    public ResponseEntity<Payment> sendPayment(@RequestBody Payment payment){
        var entity = createPaymentUseCase.create(payment);
        return  ResponseEntity.ok(entity);
    }
}
