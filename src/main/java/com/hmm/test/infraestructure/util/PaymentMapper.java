package com.hmm.test.infraestructure.util;

import com.hmm.test.domain.model.Payment;
import com.hmm.test.infraestructure.adapter.persistence.entity.PaymentDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    PaymentDocument toDocument(Payment payment);
    Payment toModel(PaymentDocument paymentDocument);
}
