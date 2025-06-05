package com.hmm.test.infraestructure.adapter.persistence;

import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.port.PaymentRepositoryPort;
import com.hmm.test.infraestructure.adapter.persistence.entity.PaymentDocument;
import com.hmm.test.infraestructure.adapter.persistence.repository.MongoPaymentRepository;
import com.hmm.test.infraestructure.util.PaymentMapper;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class MongoPaymentRepositoryAdapter implements PaymentRepositoryPort {
    private final MongoPaymentRepository mongoPaymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Payment save(Payment payment) {
        PaymentDocument document = paymentMapper.toDocument(payment);
        PaymentDocument savedDocument = mongoPaymentRepository.save(document);
        return paymentMapper.toModel(savedDocument);
    }
}
