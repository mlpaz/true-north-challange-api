package com.truenorth.challenge.api.use.cases;

import com.truenorth.challenge.api.adapter.persistence.UserJPARepository;
import com.truenorth.challenge.api.exceptions.ResourceNotFoundException;
import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.model.enums.Status;
import com.truenorth.challenge.api.resource.request.LogInRequest;
import com.truenorth.challenge.factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SignOutTest {

    @Mock
    UserJPARepository repository;

    @InjectMocks
    SignOut signOut;

    @Mock
    private Authentication auth;

    @BeforeEach
    public void initSecurityContext() {
        when(auth.getName()).thenReturn("test@gmail.com");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void givenInvalidUserWhenSignOutThenExpectError(){
        // Given
        when(repository.findFirstByEmail("test@gmail.com"))
                .thenReturn(Optional.empty());
        // When
        assertThrows(ResourceNotFoundException.class,
                () -> signOut.execute());
        // Then
        verify(repository).findFirstByEmail("test@gmail.com");
    }

    @Test
    void givenValidUserWhenLoginThenVerify(){
        // Given
        LogInRequest request = UserFactory.buildLogInRequest();
        User user = UserFactory.build();
        when(repository.findFirstByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        // When
        signOut.execute();
        //Then
        verify(repository).findFirstByEmail("test@gmail.com");
        user.setStatus(Status.INACTIVE);
        verify(repository).save(user);
    }


}
