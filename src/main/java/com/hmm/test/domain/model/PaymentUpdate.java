package com.hmm.test.domain.model;

import java.math.BigDecimal;

public record PaymentUpdate(
    String id,
    String concept,
    String status,
    BigDecimal mount,
    String email_receiver
) {}
