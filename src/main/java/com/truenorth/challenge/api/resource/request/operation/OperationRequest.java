package com.truenorth.challenge.api.resource.request.operation;


import com.truenorth.challenge.api.model.enums.OperationType;
import com.truenorth.challenge.api.resource.request.operation.input.GenerateStringInput;
import com.truenorth.challenge.api.resource.request.operation.input.MathInput;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class OperationRequest {

    @NotNull(message = "Operation Type is required.")
    private OperationType operationType;

    private MathInput mathInput;

    private GenerateStringInput generateStringInput;

}
