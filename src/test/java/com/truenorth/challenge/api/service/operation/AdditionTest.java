package com.truenorth.challenge.api.service.operation;
import com.truenorth.challenge.api.exceptions.BadRequestException;
import com.truenorth.challenge.api.resource.request.operation.OperationRequest;
import com.truenorth.challenge.api.resource.request.operation.input.MathInput;
import com.truenorth.challenge.api.service.OperationService;
import com.truenorth.challenge.factory.OperationFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AdditionTest {


    @InjectMocks
    Addition addition;


    @Test
    void givenOnlyXWhenAdditionThenCheckError(){
        // Given
        MathInput mathInput = MathInput.builder()
                .x(BigDecimal.TEN)
                .build();
        // When
        assertThrows(BadRequestException.class,
                () -> addition.execute(mathInput));
    }

    @Test
    void givenValidInputWhenExecuteOperationThenVerify(){
        // Given
        MathInput mathInput = MathInput.builder()
                .x(BigDecimal.TEN)
                .y(BigDecimal.TEN)
                .build();
        // When
        BigDecimal response = addition.execute(mathInput);
        // Then
        BigDecimal expect = BigDecimal.valueOf(20);
        assertEquals(response, expect);
    }



}
