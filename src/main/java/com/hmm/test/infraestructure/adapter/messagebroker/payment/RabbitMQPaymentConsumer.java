package com.hmm.test.infraestructure.adapter.messagebroker.payment;

import com.google.gson.Gson;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.infraestructure.adapter.mailing.SendMailNotification;
import com.hmm.test.infraestructure.config.RabbitMQPaymentConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQPaymentConsumer {
    private final SendMailNotification sendMailNotification;
    private final Gson gson;

    @RabbitListener(queues = RabbitMQPaymentConfig.QUEUE_NAME)
    public void listen(String message) {
        Payment payment = gson.fromJson(message, Payment.class);
        sendMailNotification.sendSimpleMessage(payment.getEmail_receiver(),payment.getConcept(),"You have a new payment pending to process! from " +payment.getSender() + " by amount of" + payment.getMount());
        System.out.println(payment);
    }
}
