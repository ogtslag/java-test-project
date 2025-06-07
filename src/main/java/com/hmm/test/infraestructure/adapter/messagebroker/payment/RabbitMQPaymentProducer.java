package com.hmm.test.infraestructure.adapter.messagebroker.payment;

import com.google.gson.Gson;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.infraestructure.config.RabbitMQPaymentConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQPaymentProducer {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson;

    public void sendMessagePayment(Payment payment){
        String routingKey = "routing.payments";
        rabbitTemplate.convertAndSend(RabbitMQPaymentConfig.EXCHANGE_NAME, routingKey, gson.toJson(payment));
    }
}
