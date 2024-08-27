package com.truenorth.challenge.api.adapter.controller;

import com.truenorth.challenge.api.resource.dto.RecordDTO;
import com.truenorth.challenge.api.port.in.ExecuteOperationCommand;
import com.truenorth.challenge.api.resource.request.operation.OperationRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController()
@RequestMapping("/api/v1/operation")
@Slf4j
public class HTTPOperationController {

    @Autowired
    ExecuteOperationCommand executeOperationCommand;
    @PostMapping()
    public RecordDTO executeOperation(@RequestBody @Valid OperationRequest request){
        RecordDTO response = executeOperationCommand.execute(request);
        return  response;

    }
}
