package com.test.demo.api.port.in;

import com.test.demo.api.resource.dto.RecordDTO;
import com.test.demo.api.resource.request.operation.OperationRequest;

public interface ExecuteOperationCommand {

    RecordDTO execute(OperationRequest request);
}
