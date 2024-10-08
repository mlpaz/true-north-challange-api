package com.truenorth.challenge.api.service.operation;

import com.truenorth.challenge.api.exceptions.BadRequestException;
import com.truenorth.challenge.api.model.enums.OperationType;
import com.truenorth.challenge.api.resource.request.operation.input.MathInput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SquareRoot extends AbstractOperation<BigDecimal, MathInput>{


    public SquareRoot() {
        super(OperationType.SQUARE_ROOT);
    }

    @Override
    public BigDecimal execute(MathInput request) {
        if (BigDecimal.ZERO.compareTo(request.getX()) >= 0){
            throw new BadRequestException("X must be greater than 0");
        }
        return BigDecimal.valueOf(Math.sqrt(request.getX().doubleValue()));
    }
}
