package com.hmm.test.infraestructure.adapter.messagebroker.paymentupdate;

import com.google.gson.Gson;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.model.PaymentUpdate;
import com.hmm.test.infraestructure.adapter.mailing.SendMailNotification;
import com.hmm.test.infraestructure.config.RabbitMQPaymentConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQPaymentUpdateConsumer {
    private final SendMailNotification sendMailNotification;
    private final Gson gson;

    @RabbitListener(queues = RabbitMQPaymentConfig.QUEUE_NAME_UPDATE)
    public void listen(String message) {
        PaymentUpdate payment = gson.fromJson(message, PaymentUpdate.class);
        sendMailNotification.sendSimpleMessage(
                payment.email_receiver(),
                payment.concept(),
                "The payment status of your payment now is: " +payment.status());
        System.out.println(payment);
    }
}
