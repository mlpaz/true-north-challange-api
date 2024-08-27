package com.truenorth.challenge.api.service.operation;

import com.truenorth.challenge.api.model.enums.OperationType;

public abstract class AbstractOperation<T,J> {

    private OperationType type;

    public AbstractOperation(OperationType type){
        this.type = type;
    }

    public OperationType getOperationType(){
        return type;
    };

    public abstract T execute(J request);

}
