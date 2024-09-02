package com.truenorth.challenge.api.use.cases;

import com.truenorth.challenge.api.adapter.persistence.UserJPARepository;
import com.truenorth.challenge.api.exceptions.BadRequestException;
import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.model.enums.Status;
import com.truenorth.challenge.api.resource.dto.UserTokenDTO;
import com.truenorth.challenge.api.resource.request.LogInRequest;
import com.truenorth.challenge.api.service.jwt.JwtService;
import com.truenorth.challenge.factory.UserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LogInTest {

    @Mock
    JwtService jwtService;

    @Mock
    UserJPARepository repository;

    @InjectMocks
    LogIn logIn;

    @Test
    void givenInvalidUserPassWhenLoginThenExpectError(){
        // Given
        LogInRequest request = UserFactory.buildLogInRequest();
        when(repository.findFirstByEmailAndPassword(request.getEmail(), request.getPassword()))
                .thenReturn(Optional.empty());
        // When
        assertThrows(BadRequestException.class,
                () -> logIn.execute(request));
        // Then
        verify(repository).findFirstByEmailAndPassword(request.getEmail(), request.getPassword());
    }

    @Test
    void givenValidUserPassWhenLoginThenVerify(){
        // Given
        LogInRequest request = UserFactory.buildLogInRequest();
        User user = UserFactory.build();
        when(repository.findFirstByEmailAndPassword(request.getEmail(), request.getPassword()))
                .thenReturn(Optional.of(user));
        when(jwtService.generateToken(any())).thenReturn("token");

        // When
        UserTokenDTO response = logIn.execute(request);
        //Then
        verify(repository).findFirstByEmailAndPassword(request.getEmail(), request.getPassword());
        user.setStatus(Status.ACTIVE);
        verify(repository).save(user);
        UserTokenDTO expect = UserTokenDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .credit(user.getCredit())
                .token("token")
                .build();
        assertTrue(new ReflectionEquals(expect).matches(response));
    }


}
