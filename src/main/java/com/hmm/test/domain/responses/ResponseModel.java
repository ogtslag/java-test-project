package com.hmm.test.domain.responses;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel <T>{
    private Integer statusCode;
    private T details;
    private String message;
}
