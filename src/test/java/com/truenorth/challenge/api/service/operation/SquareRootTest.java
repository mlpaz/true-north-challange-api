package com.truenorth.challenge.api.service.operation;

import com.truenorth.challenge.api.exceptions.BadRequestException;
import com.truenorth.challenge.api.resource.request.operation.input.MathInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class SquareRootTest {


    @InjectMocks
    SquareRoot squareRoot;


    @Test
    void givenLesserThanZeroYWhenAdditionThenCheckError(){
        // Given
        MathInput mathInput = MathInput.builder()
                .x(BigDecimal.valueOf(-10))
                .build();
        // When
        assertThrows(BadRequestException.class,
                () -> squareRoot.execute(mathInput));
    }


    @Test
    void givenValidInputWhenExecuteOperationThenVerify(){
        // Given
        MathInput mathInput = MathInput.builder()
                .x(BigDecimal.valueOf(4))
                .build();
        // When
        BigDecimal response = squareRoot.execute(mathInput);
        // Then
        BigDecimal expect = BigDecimal.valueOf(Math.sqrt(mathInput.getX().doubleValue()));
        assertEquals(response, expect);
    }



}
