package com.hmm.test.infraestructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQPaymentConfig {
    public static final String QUEUE_NAME = "payments_sent";
    public static final String QUEUE_NAME_UPDATE = "payments_update";
    public static final String QUEUE_NAME_PROCESSED = "payments_processed";
    public static final String EXCHANGE_NAME = "payments_exchange";

    @Bean
    public Queue queuePayment() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public Queue queuePaymentUpdate() {
        return new Queue(QUEUE_NAME_UPDATE, false);
    }

    @Bean
    public Queue queuePaymentProcessed() {
        return new Queue(QUEUE_NAME_PROCESSED, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queuePayment, TopicExchange exchange) {
        return BindingBuilder.bind(queuePayment).to(exchange).with("routing.payments.#");
    }

    @Bean
    public Binding bindingUpdate(Queue queuePaymentUpdate, TopicExchange exchange) {
        return BindingBuilder.bind(queuePaymentUpdate).to(exchange).with("routing.payments.update.#");
    }

    @Bean
    public Binding bindingProcessed(Queue queuePaymentProcessed, TopicExchange exchange) {
        return BindingBuilder.bind(queuePaymentProcessed).to(exchange).with("routing.payments.processed.#");
    }
}
