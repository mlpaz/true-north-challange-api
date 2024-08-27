package com.test.demo.api.service.operation;

import com.test.demo.api.exceptions.BadRequestException;
import com.test.demo.api.model.enums.OperationType;
import com.test.demo.api.resource.request.operation.input.MathInput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Subtraction extends AbstractOperation<BigDecimal, MathInput> {


    public Subtraction() {
        super(OperationType.SUBTRACTION);
    }

    @Override
    public BigDecimal execute(MathInput request) {
        if (request.getY() == null){
            throw new BadRequestException("Y variable is required");
        }
        return request.getX().subtract(request.getY());
    }
}
