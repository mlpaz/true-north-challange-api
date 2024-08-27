package com.test.demo.api.adapter.controller;

import com.test.demo.api.resource.dto.RecordDTO;
import com.test.demo.api.port.in.ExecuteOperationCommand;
import com.test.demo.api.resource.request.operation.OperationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController()
@RequestMapping("/api/v1/operation")
@Slf4j
public class HTTPOperationController {

    @Autowired
    ExecuteOperationCommand executeOperationCommand;
    @PostMapping()
    public RecordDTO executeOperation(@RequestBody final OperationRequest request){
        RecordDTO response = executeOperationCommand.execute(request);
        return  response;

    }
}
