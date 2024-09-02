package com.truenorth.challenge.api.service.operation;

import com.truenorth.challenge.api.exceptions.BadRequestException;
import com.truenorth.challenge.api.resource.request.operation.input.MathInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class DivisionTest {


    @InjectMocks
    Division division;


    @Test
    void givenOnlyXWhenAdditionThenCheckError(){
        // Given
        MathInput mathInput = MathInput.builder()
                .x(BigDecimal.TEN)
                .build();
        // When
        assertThrows(BadRequestException.class,
                () -> division.execute(mathInput));
    }

    @Test
    void givenZeroYWhenAdditionThenCheckError(){
        // Given
        MathInput mathInput = MathInput.builder()
                .x(BigDecimal.TEN)
                .build();
        // When
        assertThrows(BadRequestException.class,
                () -> division.execute(mathInput));
    }


    @Test
    void givenValidInputWhenExecuteOperationThenVerify(){
        // Given
        MathInput mathInput = MathInput.builder()
                .x(BigDecimal.TEN)
                .y(BigDecimal.TEN)
                .build();
        // When
        BigDecimal response = division.execute(mathInput);
        // Then
        BigDecimal expect = mathInput.getX().divide(mathInput.getY(), 10, RoundingMode.HALF_UP);
        assertEquals(response, expect);
    }



}
