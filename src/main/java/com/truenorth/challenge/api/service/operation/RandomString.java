package com.truenorth.challenge.api.service.operation;

import com.truenorth.challenge.api.adapter.api.RandomApi;
import com.truenorth.challenge.api.exceptions.BadRequestException;
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

        if(request.getLength() == null || request.getLength() < 1 || request.getLength() > 32){
            throw new BadRequestException("Length must be between 1 and 32");
        }
        if(request.getNumber() == null || request.getNumber() < 1 || request.getNumber() > 10000){
            throw new BadRequestException("Number must be between 1 and 10000");
        }
        if(!request.getDigits() && !request.getUpperLetter() && !request.getLowerLetter()){
            throw new BadRequestException("At least one of the digits or alphabet boxes must be ticked");
        }

        return randomApi.getRandomString(request);
    }
}
