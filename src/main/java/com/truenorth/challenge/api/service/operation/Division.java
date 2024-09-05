package com.truenorth.challenge.api.service.operation;

import com.truenorth.challenge.api.exceptions.BadRequestException;
import com.truenorth.challenge.api.model.enums.OperationType;
import com.truenorth.challenge.api.resource.request.operation.input.MathInput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class Division extends AbstractOperation<BigDecimal, MathInput> {


    public Division() {
        super(OperationType.DIVISION);
    }


    @Override
    public BigDecimal execute(MathInput request) {
        if (request.getY() == null){
            throw new BadRequestException("Y variable is required");
        }
        if (BigDecimal.ZERO.compareTo(request.getY()) == 0){
            throw new BadRequestException("Y cannot be 0");
        }
        return request.getX().divide(request.getY(), 10, RoundingMode.HALF_UP).stripTrailingZeros();
    }
}
