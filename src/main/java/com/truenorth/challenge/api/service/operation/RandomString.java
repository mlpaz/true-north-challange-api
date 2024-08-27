package com.truenorth.challenge.api.service.operation;

import com.truenorth.challenge.api.adapter.api.RandomApi;
import com.truenorth.challenge.api.model.enums.OperationType;
import com.truenorth.challenge.api.resource.request.operation.input.GenerateStringInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomString extends AbstractOperation<String, GenerateStringInput> {

    @Autowired
    RandomApi randomApi;
    public RandomString() {
        super(OperationType.RANDOM_STRING);
    }

    @Override
    public String execute(GenerateStringInput request) {
        return randomApi.getRandomString(request);
    }
}
