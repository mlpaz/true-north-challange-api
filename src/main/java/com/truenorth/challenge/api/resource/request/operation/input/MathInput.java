package com.truenorth.challenge.api.resource.request.operation.input;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class MathInput implements IOperationInput {

    @NotNull
    private BigDecimal x;

    private BigDecimal y;

}
