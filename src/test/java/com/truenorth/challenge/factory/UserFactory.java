package com.truenorth.challenge.factory;

import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.resource.request.LogInRequest;

import java.math.BigDecimal;
import java.util.UUID;

public class UserFactory {
    private static final String EMAIL ="test@gmail.com";
    private static final String PASSWORD ="1234";
    private static final BigDecimal CREDIT = BigDecimal.valueOf(100);
    public static LogInRequest  buildLogInRequest(){
        LogInRequest request = new LogInRequest();
        request.setEmail(EMAIL);
        request.setEmail(PASSWORD);
        return request;
    }

    public  static User build(){
        return User.builder()
                .id(UUID.randomUUID())
                .email(EMAIL)
                .credit(CREDIT)
                .build();
    }
}
