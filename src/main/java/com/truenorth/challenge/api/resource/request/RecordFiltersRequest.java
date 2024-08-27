package com.truenorth.challenge.api.resource.request;

import com.truenorth.challenge.api.model.enums.OperationType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.time.OffsetDateTime;

@Builder
@Getter
public class RecordFiltersRequest {
    OperationType operationType;
    OffsetDateTime greaterThanDate;
    OffsetDateTime lessThanDate;
    Integer page;
    Integer size;
    String orderBy;
    Sort.Direction direction;

}
