package com.hmm.test;


import com.hmm.test.application.service.PaymentService;
import com.hmm.test.domain.model.Payment;
import com.hmm.test.infraestructure.adapter.persistence.repository.MongoPaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TestApplicationTests {

	@Autowired
	PaymentService mongoPaymentRepository;

	@Test
	void createPendingPayment() {
		Payment payment = Payment.builder().build()
				.withSender("Heriberto Montoya")
				.withReceiver("Diego Aguero")
				.withEmail_receiver("developitl@gmail.com")
				.withNumber_products(1)
				.withMount(BigDecimal.valueOf(200))
				.withStatus("PENDING")
				.withConcept("Igloo");
		var saved = mongoPaymentRepository.create(payment);

		assertThat(saved.getDetails().getStatus()).isEqualTo("PENDING");
	}

	@Test
	void checkStatusPayment(){
		String id ="68448ab79dab39d2d3d0cc1d";
		var saved = mongoPaymentRepository.readPaymentStatus(id);
		assertThat(saved.getDetails()).isEqualTo("PROCESSED");

	}

	@Test
	void reviewChangeStatus(){
		String id ="6844a4a2f8b52df4237b8593";
		var saved = mongoPaymentRepository.readPaymentStatus(id);
		var modified = mongoPaymentRepository.modifyStatus(id,"IN_PROCESS");
		assertThat(saved.getDetails()).isEqualTo("PENDING");
		assertThat(modified.getDetails().getStatus()).isEqualTo("IN_PROCESS");
	}

}