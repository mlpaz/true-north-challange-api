package com.truenorth.challenge.factory;


import com.truenorth.challenge.api.model.Operation;
import com.truenorth.challenge.api.model.Record;
import com.truenorth.challenge.api.resource.request.RecordFiltersRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class RecordFactory {
    private static final UUID ID = UUID.randomUUID();
    private static final BigDecimal AMOUNT = BigDecimal.TEN;
    private static final Operation OPERATION = OperationFactory.build();
    private static final BigDecimal USER_BALANCE = BigDecimal.TEN;
    private static final String OPERATION_RESPONSE ="10";
    public static Record  build(){
        return Record.builder()
                .id(ID)
                .amount(OPERATION.getCost())
                .userBalance(USER_BALANCE)
                .operationResponse(OPERATION_RESPONSE)
                .operation(OPERATION)
                .build();
    }

    public static Record build(Operation operation, UUID userId, BigDecimal userBalance) {
        return Record.builder()
                .userId(userId)
                .userBalance(userBalance)
                .amount(operation.getCost())
                .operationResponse(OPERATION_RESPONSE)
                .operation(operation)
                .build();
    }

    public static RecordFiltersRequest buildGetRequest(){
        return RecordFiltersRequest.builder()
                .size(10)
                .page(0)
                .lessThanDate(OffsetDateTime.now())
                .greaterThanDate(OffsetDateTime.now())
                .orderBy("createdAt")
                .direction(Sort.Direction.ASC)
                .build();
    }

}
