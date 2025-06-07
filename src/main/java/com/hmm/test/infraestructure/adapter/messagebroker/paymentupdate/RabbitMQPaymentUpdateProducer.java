package com.hmm.test.infraestructure.adapter.messagebroker.paymentupdate;

import com.google.gson.Gson;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.infraestructure.config.RabbitMQPaymentConfig;
import com.hmm.test.infraestructure.util.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQPaymentUpdateProducer {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson;
    private final PaymentMapper paymentMapper;

    public void sendMessagePaymentUpdate(Payment payment){
        String routingKey = "routing.payments.update";
        rabbitTemplate.convertAndSend(
            RabbitMQPaymentConfig.QUEUE_NAME_UPDATE,
            gson.toJson(paymentMapper.toPaymentUpdate(payment))
        );
    }
}
