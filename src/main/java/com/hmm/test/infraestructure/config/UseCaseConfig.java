package com.hmm.test.infraestructure.config;

import com.hmm.test.domain.port.PaymentRepositoryPort;
import com.hmm.test.domain.port.ReceiverBalanceRepositoryPort;
import com.hmm.test.domain.port.StatusRepositoryPort;
import com.hmm.test.infraestructure.adapter.persistence.MongoPaymentRepositoryAdapter;
import com.hmm.test.infraestructure.adapter.persistence.MongoReceiverBalanceAdapter;
import com.hmm.test.infraestructure.adapter.persistence.MongoStatusRepositoryAdapter;
import com.hmm.test.infraestructure.adapter.persistence.repository.MongoPaymentRepository;
import com.hmm.test.infraestructure.adapter.persistence.repository.MongoReceiverBalanceRepository;
import com.hmm.test.infraestructure.adapter.persistence.repository.MongoStatusRepository;
import com.hmm.test.infraestructure.util.PaymentMapper;
import com.hmm.test.infraestructure.util.ReceiverBalanceMapper;
import com.hmm.test.infraestructure.util.StatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {
    private final PaymentMapper paymentMapper;
    private final MongoPaymentRepository mongoPaymentRepository;
    private final StatusMapper statusMapper;
    private final MongoStatusRepository mongoStatusRepository;
    private final MongoReceiverBalanceRepository mongoReceiverBalanceRepository;
    private final ReceiverBalanceMapper receiverBalanceMapper;

    @Bean
    public PaymentRepositoryPort paymentRepository(){
        return  new MongoPaymentRepositoryAdapter(mongoPaymentRepository, paymentMapper);
    }

    @Bean
    public StatusRepositoryPort statusRepositoryPort(){
        return new MongoStatusRepositoryAdapter(mongoStatusRepository, statusMapper);
    }

    @Bean
    public ReceiverBalanceRepositoryPort receiverBalanceRepositoryPort(){
        return new MongoReceiverBalanceAdapter(mongoReceiverBalanceRepository, receiverBalanceMapper);
    }


}
