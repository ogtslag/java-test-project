package com.hmm.test.domain.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ModifyPaymentRequest {
    private String id;
    private String status;
}
