package com.truenorth.challenge.api.adapter.controller;

import com.truenorth.challenge.api.model.enums.OperationType;
import com.truenorth.challenge.api.port.in.DeleteRecordCommand;
import com.truenorth.challenge.api.port.in.GetRecordsCommand;
import com.truenorth.challenge.api.resource.dto.RecordDTO;
import com.truenorth.challenge.api.resource.request.RecordFiltersRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.UUID;


@RestController()
@RequestMapping("/api/v1/record")
@Slf4j
public class HTTPRecordController {


    @Autowired
    GetRecordsCommand getRecords;

    @Autowired
    DeleteRecordCommand deleteRecordCommand;

    @GetMapping
    public Page<RecordDTO> getRecords(@RequestParam(defaultValue = "10") Integer size,
                                      @RequestParam(defaultValue = "0") Integer page,
                                      @RequestParam(required = false) OperationType operationType,
                                      @RequestParam(required = false) OffsetDateTime greaterThanDate,
                                      @RequestParam(required = false) OffsetDateTime lessThanDate,
                                      @RequestParam(defaultValue = "createdAt") String orderBy,
                                      @RequestParam(defaultValue = "DESC") Sort.Direction direction) {

        RecordFiltersRequest filters = RecordFiltersRequest.builder()
                .operationType(operationType)
                .greaterThanDate(greaterThanDate)
                .lessThanDate(lessThanDate)
                .page(page)
                .size(size)
                .orderBy(orderBy)
                .direction(direction)
                .build();
        return  getRecords.execute(filters);
    }

    @DeleteMapping("/{id}")
    public RecordDTO deleteRecord(@PathVariable UUID id) {
        return deleteRecordCommand.execute(id);
    }


}
