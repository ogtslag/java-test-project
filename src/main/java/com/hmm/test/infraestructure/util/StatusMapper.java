package com.hmm.test.infraestructure.util;

import com.hmm.test.domain.model.Status;
import com.hmm.test.infraestructure.adapter.persistence.entity.StatusDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatusMapper {
    List<Status> fromListModel(List<StatusDocument> list);
}
