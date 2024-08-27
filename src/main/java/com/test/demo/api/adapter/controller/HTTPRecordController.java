package com.test.demo.api.adapter.controller;

import com.test.demo.api.port.in.DeleteRecordCommand;
import com.test.demo.api.port.in.GetRecordsCommand;
import com.test.demo.api.resource.dto.RecordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
                                      @RequestParam(defaultValue = "0") Integer page ) {
        return  getRecords.execute(PageRequest.of(page, size));
    }

    @DeleteMapping("/{id}")
    public RecordDTO deleteRecord(@PathVariable UUID id) {
        return deleteRecordCommand.execute(id);
    }


}
