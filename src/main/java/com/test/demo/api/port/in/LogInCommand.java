package com.test.demo.api.port.in;

import com.test.demo.api.resource.dto.RecordDTO;
import com.test.demo.api.resource.dto.UserTokenDTO;
import com.test.demo.api.resource.request.LogInRequest;
import com.test.demo.api.resource.request.operation.OperationRequest;

public interface LogInCommand {

    UserTokenDTO execute(LogInRequest request);
}
