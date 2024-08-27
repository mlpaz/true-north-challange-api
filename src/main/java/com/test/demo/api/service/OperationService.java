package com.test.demo.api.service;

import com.test.demo.api.model.enums.OperationType;
import com.test.demo.api.resource.request.operation.OperationRequest;
import com.test.demo.api.resource.request.operation.input.IOperationInput;
import com.test.demo.api.service.operation.AbstractOperation;
import com.test.demo.api.service.operation.OperationMap;
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
