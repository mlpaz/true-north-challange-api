package com.test.demo.api.service.operation;

import com.test.demo.api.model.enums.OperationType;
import com.test.demo.api.resource.request.operation.input.MathInput;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SquareRoot extends AbstractOperation<BigDecimal, MathInput>{


    public SquareRoot() {
        super(OperationType.SQUARE_ROOT);
    }

    @Override
    public BigDecimal execute(MathInput request) {
        return BigDecimal.valueOf(Math.sqrt(request.getX().doubleValue()));
    }
}
