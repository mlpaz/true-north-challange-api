package com.truenorth.challenge.api.use.cases;

import com.truenorth.challenge.api.adapter.persistence.OperationJPARepository;
import com.truenorth.challenge.api.adapter.persistence.RecordJPARepository;
import com.truenorth.challenge.api.adapter.persistence.UserJPARepository;
import com.truenorth.challenge.api.exceptions.OperationNotAcceptableException;
import com.truenorth.challenge.api.exceptions.ResourceNotFoundException;
import com.truenorth.challenge.api.factory.RecordDTOFactory;
import com.truenorth.challenge.api.model.Operation;
import com.truenorth.challenge.api.model.Record;
import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.model.enums.Status;
import com.truenorth.challenge.api.resource.dto.RecordDTO;
import com.truenorth.challenge.api.resource.request.LogInRequest;
import com.truenorth.challenge.api.resource.request.operation.OperationRequest;
import com.truenorth.challenge.api.service.OperationService;
import com.truenorth.challenge.factory.OperationFactory;
import com.truenorth.challenge.factory.RecordFactory;
import com.truenorth.challenge.factory.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ExecuteOperationTest {

    @Mock
    UserJPARepository userRepository;

    @Mock
    RecordJPARepository recordRepository;

    @Mock
    OperationJPARepository operationRepository;

    @Mock
    OperationService operationService;

    @InjectMocks
    ExecuteOperation executeOperation;

    @Mock
    private Authentication auth;


    @Test
    void givenInvalidOperationWhenExecuteOperationThenExpectError(){
        // Given
        OperationRequest request = OperationFactory.buildRequest();
        when(operationRepository.findByType(request.getOperationType()))
                .thenReturn(Optional.empty());
        // When
        assertThrows(ResourceNotFoundException.class, () -> executeOperation.execute(request));
        // Then
        verify(operationRepository).findByType(request.getOperationType());
    }

    @Test
    void givenInvalidUsernameWhenExecuteOperationThenExpectError(){
        // Given
        when(auth.getName()).thenReturn("test@gmail.com");
        SecurityContextHolder.getContext().setAuthentication(auth);
        OperationRequest request = OperationFactory.buildRequest();
        Operation operation = OperationFactory.build();
        when(operationRepository.findByType(request.getOperationType()))
                .thenReturn(Optional.of(operation));
        when(userRepository.findFirstByEmail("test@gmail.com"))
                .thenReturn(Optional.empty());
        // When
        assertThrows(ResourceNotFoundException.class, () -> executeOperation.execute(request));
        // Then
        verify(operationRepository).findByType(request.getOperationType());
        verify(userRepository).findFirstByEmail("test@gmail.com");
    }

    @Test
    void givenInsufficientCreditWhenExecuteOperationThenExpectError(){
        // Given
        when(auth.getName()).thenReturn("test@gmail.com");
        SecurityContextHolder.getContext().setAuthentication(auth);

        OperationRequest request = OperationFactory.buildRequest();
        Operation operation = OperationFactory.build();
        User user = UserFactory.build();
        user.setCredit(BigDecimal.ZERO);

        when(operationRepository.findByType(request.getOperationType()))
                .thenReturn(Optional.of(operation));
        when(userRepository.findFirstByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        when(operationService.executeOperation(request)).thenReturn("10");
        // When
        assertThrows(OperationNotAcceptableException.class, () -> executeOperation.execute(request));
        // Then
        verify(operationRepository).findByType(request.getOperationType());
        verify(userRepository).findFirstByEmail("test@gmail.com");
        verify(operationService).executeOperation(request);
    }

    @Test
    void givenValidRequestWhenExecuteOperationThenVerify(){
        // Given
        when(auth.getName()).thenReturn("test@gmail.com");
        SecurityContextHolder.getContext().setAuthentication(auth);

        OperationRequest request = OperationFactory.buildRequest();
        Operation operation = OperationFactory.build();
        User user = UserFactory.build();
        String operationResponse = "10";
        BigDecimal userBalance = user.getCredit().subtract(operation.getCost());
        Record recordSaved = RecordFactory.build(operation, user.getId(), userBalance);
        Record record = RecordFactory.build(operation, user.getId(), userBalance);
        record.setId(UUID.randomUUID());

        when(operationRepository.findByType(request.getOperationType()))
                .thenReturn(Optional.of(operation));
        when(userRepository.findFirstByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));
        when(operationService.executeOperation(request)).thenReturn(operationResponse);
        when(recordRepository.save(recordSaved)).thenReturn(record);

        // When
        RecordDTO response = executeOperation.execute(request);
        // Then
        verify(operationRepository).findByType(request.getOperationType());
        verify(userRepository).findFirstByEmail("test@gmail.com");
        verify(operationService).executeOperation(request);

        user.setCredit(userBalance);
        verify(userRepository).save(user);

        verify(recordRepository).save(recordSaved);

        RecordDTO expect = RecordDTOFactory.fromModel(record);

        assertTrue(new ReflectionEquals(expect).matches(response));
    }


}
