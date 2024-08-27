package com.test.demo.api.service.operation;

import com.test.demo.api.model.enums.OperationType;
import com.test.demo.api.resource.request.operation.input.GenerateStringInput;
import org.springframework.stereotype.Component;

@Component
public class RandomString extends AbstractOperation<String, GenerateStringInput> {

    public RandomString() {
        super(OperationType.SQUARE_ROOT);
    }

    @Override
    public String execute(GenerateStringInput request) {
        return null;
    }
}
