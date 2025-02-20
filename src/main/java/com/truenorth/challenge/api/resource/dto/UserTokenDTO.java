package com.truenorth.challenge.api.resource.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserTokenDTO {

    private UUID id;

    private String email;

    private BigDecimal credit;

    private String token;

}