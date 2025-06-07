package com.hmm.test.infraestructure.util;

import com.hmm.test.domain.model.ReceiverBalance;
import com.hmm.test.infraestructure.adapter.persistence.entity.ReceiverBalanceDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReceiverBalanceMapper {
    ReceiverBalanceDocument toDocument(ReceiverBalance receiverBalance);
    ReceiverBalance toModel(ReceiverBalanceDocument receiverBalanceDocument);
}
