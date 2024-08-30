package com.truenorth.challenge.api.resource.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;


@Builder
@Getter
public class UserTokenDTO {

    private UUID id;

    private String email;

    private BigDecimal credit;

    private String token;

}