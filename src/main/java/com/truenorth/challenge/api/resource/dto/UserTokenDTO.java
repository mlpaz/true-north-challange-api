package com.truenorth.challenge.api.resource.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Builder
@Getter
public class UserTokenDTO {

    private UUID id;

    private String email;

    private String token;

}