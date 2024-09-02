package com.truenorth.challenge.api.service;
import com.truenorth.challenge.api.resource.request.operation.OperationRequest;
import com.truenorth.challenge.api.service.operation.Addition;
import com.truenorth.challenge.api.service.operation.OperationMap;
import com.truenorth.challenge.api.service.operation.RandomString;
import com.truenorth.challenge.factory.OperationFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OperationServiceTest {

    @Mock
    OperationMap operationMap;

    @InjectMocks
    OperationService service;

    @Spy
    Addition addition;

    @Mock
    RandomString randomString;

    @Test
    void givenMathOperationWhenExecuteOperationThenVerify(){
        // Given
        OperationRequest request = OperationFactory.buildMathInput();
        when(operationMap.getOperation(any())).thenReturn(addition);
        // When
        String response = service.executeOperation(request);
        // Then
        String expect = "20";
        verify(addition).execute(request.getMathInput());
        assertEquals(response, expect);
    }

    @Test
    void givenStringOperationWhenExecuteOperationThenVerify(){
        // Given
        String randomStringResponse = "svd34DS";
        OperationRequest request = OperationFactory.buildStringInput();
        when(operationMap.getOperation(any())).thenReturn(randomString);
        when(randomString.execute(request.getGenerateStringInput())).thenReturn(randomStringResponse);
        // When
        String response = service.executeOperation(request);
        // Then
        verify(randomString).execute(request.getGenerateStringInput());
        assertEquals(response, randomStringResponse);
    }



}
