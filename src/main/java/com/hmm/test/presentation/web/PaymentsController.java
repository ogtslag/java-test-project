package com.hmm.test.presentation.web;

import com.hmm.test.application.usecase.CreatePaymentUseCase;
import com.hmm.test.application.usecase.ModifyPaymentStatusUseCase;
import com.hmm.test.application.usecase.ReadPaymentStatusUseCase;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.request.ModifyPaymentRequest;
import com.hmm.test.domain.responses.ResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentsController {
    private final CreatePaymentUseCase createPaymentUseCase;
    private final ReadPaymentStatusUseCase readPaymentStatus;
    private final ModifyPaymentStatusUseCase modifyPaymentStatusUseCase;

    @PostMapping("")
    public ResponseEntity<ResponseModel<Payment>> sendPayment(@RequestBody Payment payment){
        if(payment == null) return  ResponseEntity.badRequest().build();
        var entity = createPaymentUseCase.create(payment);
        return  ResponseEntity.ok(entity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<String>> readPaymentStatus(@PathVariable String id){
        if(id == null) return ResponseEntity.badRequest().build();

        var entity = readPaymentStatus.readPaymentStatus(id);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("")
    public ResponseEntity<ResponseModel<Payment>> updatePayment(@RequestBody ModifyPaymentRequest payment){
        if(payment == null)
            return  ResponseEntity.badRequest().build();

        var entity = modifyPaymentStatusUseCase.modifyStatus(payment.getId(), payment.getStatus());
        return ResponseEntity.status(entity.getStatusCode()).body(entity);
    }
}
