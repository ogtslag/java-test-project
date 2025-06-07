package com.hmm.test.domain.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ReceiverBalance {
    private String id;
    private String receiver;
    private BigDecimal amount;
}