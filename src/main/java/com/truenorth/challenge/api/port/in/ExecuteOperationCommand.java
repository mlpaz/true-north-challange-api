package com.truenorth.challenge.api.port.in;

import com.truenorth.challenge.api.resource.dto.RecordDTO;
import com.truenorth.challenge.api.resource.request.operation.OperationRequest;

public interface ExecuteOperationCommand {

    RecordDTO execute(OperationRequest request);
}
