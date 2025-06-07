package com.hmm.test.domain.port;

import com.hmm.test.domain.model.Status;

import java.util.List;

public interface StatusRepositoryPort {
    List<Status> findAll();
}
