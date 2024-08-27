package com.test.demo.api.service.operation;

import com.test.demo.api.model.enums.OperationType;

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
