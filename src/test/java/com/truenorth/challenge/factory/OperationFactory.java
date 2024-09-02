package com.truenorth.challenge.factory;


import com.truenorth.challenge.api.model.Operation;
import com.truenorth.challenge.api.model.Record;
import com.truenorth.challenge.api.model.enums.OperationType;
import com.truenorth.challenge.api.resource.request.operation.OperationRequest;
import com.truenorth.challenge.api.resource.request.operation.input.GenerateStringInput;
import com.truenorth.challenge.api.resource.request.operation.input.MathInput;

import java.math.BigDecimal;
import java.util.UUID;

import static com.truenorth.challenge.api.model.enums.OperationType.RANDOM_STRING;

public class OperationFactory {
    private static final UUID ID = UUID.randomUUID();
    private static final BigDecimal COST = BigDecimal.TEN;
    private static final OperationType MATH_TYPE = OperationType.ADDITION;
    public static Operation build(){
        return Operation.builder()
                .id(ID)
                .cost(COST)
                .type(MATH_TYPE)
                .build();
    }


    public static OperationRequest buildRequest(){
        OperationRequest request = new OperationRequest();
        request.setOperationType(MATH_TYPE);
        return request;
    }

    public static OperationRequest buildMathInput(){
        OperationRequest request = new OperationRequest();
        request.setOperationType(MATH_TYPE);
        MathInput mathInput = MathInput.builder()
                .x(BigDecimal.TEN)
                .y(BigDecimal.TEN)
                .build();
        request.setMathInput(mathInput);
        return request;
    }




    public static OperationRequest buildStringInput(){
        OperationRequest request = new OperationRequest();
        request.setOperationType(RANDOM_STRING);
        GenerateStringInput generateStringInput = GenerateStringInput.builder()
                .digits(true)
                .length(10)
                .number(10)
                .build();
        request.setGenerateStringInput(generateStringInput);
        return request;
    }
}
