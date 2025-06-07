package com.hmm.test.infraestructure.adapter.persistence;

import com.hmm.test.domain.model.Status;
import com.hmm.test.domain.port.StatusRepositoryPort;
import com.hmm.test.infraestructure.adapter.persistence.repository.MongoStatusRepository;
import com.hmm.test.infraestructure.util.StatusMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MongoStatusRepositoryAdapter implements StatusRepositoryPort {
    private final MongoStatusRepository mongoStatusRepository;
    private final StatusMapper statusMapper;

    @Override
    public List<Status> findAll() {
        var status = mongoStatusRepository.findAll();
        return statusMapper.fromListModel(status);
    }
}
