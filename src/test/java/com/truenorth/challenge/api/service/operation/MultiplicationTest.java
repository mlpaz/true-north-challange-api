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
public class MultiplicationTest {


    @InjectMocks
    Multiplication multiplication;


    @Test
    void givenOnlyXWhenAdditionThenCheckError(){
        // Given
        MathInput mathInput = MathInput.builder()
                .x(BigDecimal.TEN)
                .build();
        // When
        assertThrows(BadRequestException.class,
                () -> multiplication.execute(mathInput));
    }

    @Test
    void givenValidInputWhenExecuteOperationThenVerify(){
        // Given
        MathInput mathInput = MathInput.builder()
                .x(BigDecimal.TEN)
                .y(BigDecimal.TEN)
                .build();
        // When
        BigDecimal response = multiplication.execute(mathInput);
        // Then
        BigDecimal expect = BigDecimal.valueOf(100);
        assertEquals(response, expect);
    }



}
