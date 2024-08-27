package com.truenorth.challenge.api.adapter.controller;

import com.truenorth.challenge.api.port.in.LogInCommand;
import com.truenorth.challenge.api.port.in.SignOutCommand;
import com.truenorth.challenge.api.resource.dto.UserTokenDTO;
import com.truenorth.challenge.api.resource.request.LogInRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api/v1/user")
@Slf4j
public class HTTPUserController {


    @Autowired
    LogInCommand logInCommand;

    @Autowired
    SignOutCommand signOutCommand;

    @PostMapping(value = "/login")
    @ResponseBody
    public UserTokenDTO login(@RequestBody @Valid final LogInRequest request)  {
        return  logInCommand.execute(request);
    }

    @PostMapping(value = "/signOut")
    public void signOut()  {
        signOutCommand.execute();
    }


}
