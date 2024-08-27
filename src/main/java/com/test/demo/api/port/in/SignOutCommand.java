package com.test.demo.api.port.in;

import com.test.demo.api.resource.dto.UserTokenDTO;
import com.test.demo.api.resource.request.LogInRequest;

public interface SignOutCommand {

    void execute();
}
