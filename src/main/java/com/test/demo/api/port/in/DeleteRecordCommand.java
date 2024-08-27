package com.test.demo.api.port.in;

import com.test.demo.api.resource.dto.RecordDTO;
import com.test.demo.api.resource.dto.UserTokenDTO;
import com.test.demo.api.resource.request.LogInRequest;

import java.util.UUID;

public interface DeleteRecordCommand {

    RecordDTO execute(UUID id);
}
