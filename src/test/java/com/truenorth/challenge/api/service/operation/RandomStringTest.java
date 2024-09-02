package com.truenorth.challenge.api.service.operation;

import com.truenorth.challenge.api.adapter.api.RandomApi;
import com.truenorth.challenge.api.exceptions.BadRequestException;
import com.truenorth.challenge.api.resource.request.operation.input.GenerateStringInput;
import com.truenorth.challenge.api.resource.request.operation.input.MathInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RandomStringTest {

    @Mock
    RandomApi randomApi;

    @InjectMocks
    RandomString randomString;


    @Test
    void givenInvalidLengthWhenRandomStringThenCheckError(){
        // Given
        GenerateStringInput invalidInput = GenerateStringInput.builder()
                .digits(true)
                .number(10)
                .build();

        assertThrows(BadRequestException.class,
                () -> randomString.execute(invalidInput));

        invalidInput.setLength(100);

        assertThrows(BadRequestException.class,
                () -> randomString.execute(invalidInput));

        invalidInput.setLength(-10);

        assertThrows(BadRequestException.class,
                () -> randomString.execute(invalidInput));
    }

    @Test
    void givenInvalidNumberWhenRandomStringThenCheckError(){
        // Given
        GenerateStringInput invalidInput = GenerateStringInput.builder()
                .digits(true)
                .length(10)
                .build();

        assertThrows(BadRequestException.class,
                () -> randomString.execute(invalidInput));

        invalidInput.setLength(10001);

        assertThrows(BadRequestException.class,
                () -> randomString.execute(invalidInput));

        invalidInput.setLength(-10);

        assertThrows(BadRequestException.class,
                () -> randomString.execute(invalidInput));
    }

    @Test
    void givenInvalidOptionsWhenRandomStringThenCheckError(){
        // Given
        GenerateStringInput invalidInput = GenerateStringInput.builder()
                .length(10)
                .number(10)
                .digits(false)
                .upperLetter(false)
                .lowerLetter(false)
                .build();

        assertThrows(BadRequestException.class,
                () -> randomString.execute(invalidInput));
    }

    @Test
    void givenValidInputWhenRandomStringThenVerify(){
        // Given

        GenerateStringInput invalidInput = GenerateStringInput.builder()
                .length(10)
                .number(10)
                .digits(false)
                .upperLetter(true)
                .lowerLetter(false)
                .build();
        when(randomApi.getRandomString(invalidInput)).thenReturn("1234");

        String response = randomString.execute(invalidInput);

        assertEquals(response, "1234");
    }

}
