package com.truenorth.challenge.api.service.operation;

import com.truenorth.challenge.api.model.enums.OperationType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OperationMap  {

    @Autowired
    Addition addition;
    @Autowired
    Division division;
    @Autowired
    Multiplication multiplication;
    @Autowired
    RandomString randomString;
    @Autowired
    SquareRoot squareRoot;
    @Autowired
    Subtraction subtraction;

    private Map<OperationType, AbstractOperation> map;

    @PostConstruct
    public  void init(){
        map = new HashMap<>();
        map.put(addition.getOperationType(), addition);
        map.put(division.getOperationType(), division);
        map.put(multiplication.getOperationType(), multiplication);
        map.put(randomString.getOperationType(), randomString);
        map.put(squareRoot.getOperationType(), squareRoot);
        map.put(subtraction.getOperationType(), subtraction);
    }


    public  AbstractOperation getOperation(OperationType type){
        return map.get(type);
    }
}
