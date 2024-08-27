package com.test.demo.api.resource.dto;


import com.test.demo.api.model.Operation;
import com.test.demo.api.model.Record;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


@Builder
@Getter
public class RecordDTO {

    UUID id;

    private BigDecimal userBalance;

    private String operationResponse;

    private UUID userId;

    private Operation operation;

    private OffsetDateTime date;

}
