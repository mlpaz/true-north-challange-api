package com.truenorth.challenge.api.resource.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LogInRequest {
    @NotEmpty
    String email;
    @NotEmpty
    String password;
}
