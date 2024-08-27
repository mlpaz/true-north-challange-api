package com.test.demo.api.resource.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Valid
public class LogInRequest {
    @NotEmpty
    String email;
    @NotEmpty
    String password;
}
