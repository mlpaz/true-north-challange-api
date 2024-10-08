package com.truenorth.challenge.api.service.operation;

import com.truenorth.challenge.api.exceptions.BadRequestException;
import com.truenorth.challenge.api.model.enums.OperationType;
import com.truenorth.challenge.api.resource.request.operation.input.MathInput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Multiplication extends AbstractOperation<BigDecimal, MathInput> {


    public Multiplication() {
        super(OperationType.MULTIPLICATION);
    }

    @Override
    public BigDecimal execute(MathInput request) {
        if (request.getY() == null){
            throw new BadRequestException("Y variable is required");
        }
        return request.getX().multiply(request.getY());
    }
}
