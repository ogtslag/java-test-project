package com.hmm.test.infraestructure.adapter.persistence.messagebroker;

import com.google.gson.Gson;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.infraestructure.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson;

    public void sendMessagePayment(Payment payment){
        String routingKey = "routing.payments";
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, routingKey, gson.toJson(payment));
    }
}
