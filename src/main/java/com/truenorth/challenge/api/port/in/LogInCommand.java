package com.truenorth.challenge.api.port.in;

import com.truenorth.challenge.api.resource.dto.UserTokenDTO;
import com.truenorth.challenge.api.resource.request.LogInRequest;

public interface LogInCommand {

    UserTokenDTO execute(LogInRequest request);
}
