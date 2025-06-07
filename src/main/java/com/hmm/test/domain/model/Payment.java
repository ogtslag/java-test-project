package com.hmm.test.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@With
public class Payment {
    private String id;
    private String concept;
    private Integer number_products;
    private String sender;
    private String receiver;
    private String email_receiver;
    private BigDecimal mount;
    private String status;
    private LocalDate created_date = LocalDate.now();
    private LocalDate modified_date = LocalDate.now();
}
