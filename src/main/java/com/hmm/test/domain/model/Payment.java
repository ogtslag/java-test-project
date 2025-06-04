package com.hmm.test.domain.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Payment {
    public String id;

    public String concept;

    public Integer products;

    public String sender;

    public  String receiver;
}
