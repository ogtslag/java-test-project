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
    public Optional<Payment> findById(String id) {
        Optional<PaymentDocument> document = mongoPaymentRepository.findById(id);
        return document.map(paymentMapper::toModel);
    }

    @Override
    public Optional<Payment> modifyStatus(String id, String status) {
        Optional<PaymentDocument> document = mongoPaymentRepository.findById(id);
        if(document.isPresent()) {
            document.get().setStatus(status);
            mongoPaymentRepository.save(document.get());
            return Optional.of(paymentMapper.toModel(document.get()));
        }
        return Optional.empty();
    }
}
