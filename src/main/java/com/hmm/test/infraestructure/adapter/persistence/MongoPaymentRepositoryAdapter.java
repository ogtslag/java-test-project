package com.hmm.test.infraestructure.adapter.persistence;

import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.port.PaymentRepositoryPort;
import com.hmm.test.infraestructure.adapter.persistence.entity.PaymentDocument;
import com.hmm.test.infraestructure.adapter.persistence.repository.MongoPaymentRepository;
import com.hmm.test.infraestructure.util.PaymentMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
public class MongoPaymentRepositoryAdapter implements PaymentRepositoryPort {
    private final MongoPaymentRepository mongoPaymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Payment save(Payment payment) {
        PaymentDocument document = paymentMapper.toDocument(payment);
        System.out.println(document.toString());
        PaymentDocument savedDocument = mongoPaymentRepository.save(document);
        return paymentMapper.toModel(savedDocument);
    }

    @Override
    public Payment findById(String id) {
        Optional<PaymentDocument> document = mongoPaymentRepository.findById(id);
        return  paymentMapper.toModel(document.orElseThrow());
    }

    @Override
    public Payment modifyStatus(String id, String status) {
        Optional<PaymentDocument> document = mongoPaymentRepository.findById(id);
        document.orElseThrow().setStatus(status);
        mongoPaymentRepository.save(document.orElseThrow());
        return  paymentMapper.toModel(document.orElseThrow());
    }
}
