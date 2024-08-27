package com.truenorth.challenge.api.service;

import com.truenorth.challenge.api.model.enums.OperationType;
import com.truenorth.challenge.api.resource.request.operation.OperationRequest;
import com.truenorth.challenge.api.resource.request.operation.input.IOperationInput;
import com.truenorth.challenge.api.service.operation.AbstractOperation;
import com.truenorth.challenge.api.service.operation.OperationMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    @Autowired
    OperationMap operationMap;

    public String executeOperation(OperationRequest request) {

        AbstractOperation operation = operationMap.getOperation(request.getOperationType());

        IOperationInput input;
        if(OperationType.RANDOM_STRING.equals(request.getOperationType())){
            input  = request.getGenerateStringInput();
        } else {
            input  = request.getMathInput();

        }
        return operation.execute(input).toString();

    }





}
