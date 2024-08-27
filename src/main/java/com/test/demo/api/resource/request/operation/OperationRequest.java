package com.test.demo.api.resource.request.operation;


import com.test.demo.api.model.enums.OperationType;
import com.test.demo.api.resource.request.operation.input.GenerateStringInput;
import com.test.demo.api.resource.request.operation.input.MathInput;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class OperationRequest {

    @NotEmpty(message = "Operation Type is required.")
    private OperationType operationType;

    private MathInput mathInput;

    private GenerateStringInput generateStringInput;

}
