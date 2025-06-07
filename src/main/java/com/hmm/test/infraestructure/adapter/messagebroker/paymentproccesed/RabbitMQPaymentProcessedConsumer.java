package com.hmm.test.infraestructure.adapter.messagebroker.paymentproccesed;

import com.google.gson.Gson;
import com.hmm.test.application.usecase.CreateReceiverBalance;
import com.hmm.test.application.usecase.ReadReceiverBalanceUseCase;
import com.hmm.test.application.usecase.UpdateReceiverBalanceUseCase;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.domain.model.ReceiverBalance;
import com.hmm.test.infraestructure.config.RabbitMQPaymentConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQPaymentProcessedConsumer {
    private final Gson gson;
    private final CreateReceiverBalance createReceiverBalance;
    private final ReadReceiverBalanceUseCase readReceiverBalance;
    private final UpdateReceiverBalanceUseCase updateReceiverBalanceUseCase;

    @RabbitListener(queues = RabbitMQPaymentConfig.QUEUE_NAME_PROCESSED)
    public void listen(String message) {
        Payment payment = gson.fromJson(message, Payment.class);
        System.out.println(payment);

        var balance = readReceiverBalance.findByReceiver(payment.getReceiver());
        if(balance.isEmpty()) {
            ReceiverBalance balanceModel = ReceiverBalance.builder().build();
            balanceModel.setReceiver(payment.getReceiver());
            balanceModel.setAmount(payment.getMount());
            createReceiverBalance.create(balanceModel);
        }
        else {
            updateReceiverBalanceUseCase.update(balance.get());
        }
    }
}
