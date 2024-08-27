package com.test.demo.api.adapter.controller;

import com.test.demo.api.adapter.persistence.UserJPARepository;
import com.test.demo.api.exceptions.BadRequestException;
import com.test.demo.api.model.User;
import com.test.demo.api.port.in.LogInCommand;
import com.test.demo.api.port.in.SignOutCommand;
import com.test.demo.api.resource.dto.UserTokenDTO;
import com.test.demo.api.resource.request.LogInRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


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
    public UserTokenDTO login(@RequestBody final LogInRequest request)  {
        return  logInCommand.execute(request);
    }

    @PostMapping(value = "/signOut")
    public void signOut(HttpServletRequest request, HttpServletResponse response)  {
        signOutCommand.execute();
    }


}
