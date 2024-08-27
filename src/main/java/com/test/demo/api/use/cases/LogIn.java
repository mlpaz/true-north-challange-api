package com.test.demo.api.use.cases;

import com.test.demo.api.adapter.persistence.UserJPARepository;
import com.test.demo.api.exceptions.ResourceNotFoundException;
import com.test.demo.api.exceptions.UnauthorizedException;
import com.test.demo.api.model.User;
import com.test.demo.api.model.enums.Status;
import com.test.demo.api.port.in.LogInCommand;
import com.test.demo.api.resource.dto.UserDetailsDTO;
import com.test.demo.api.resource.dto.UserTokenDTO;
import com.test.demo.api.resource.request.LogInRequest;
import com.test.demo.api.service.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Slf4j
@Transactional
public class LogIn implements LogInCommand {

    @Autowired
    UserJPARepository repository;

    @Autowired
    JwtService JwtService;

    @Override
    public UserTokenDTO execute(LogInRequest request) {
        log.info("Try to authenticate user {}", request.getEmail());
        User user = repository.findFirstByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow( () -> new UnauthorizedException("Invalid user and password"));
        log.info("Try to ACTIVE status to user {}", request.getEmail());
        user.setStatus(Status.ACTIVE);
        repository.save(user);


        String token = JwtService.generateToken(UserDetailsDTO.builder()
                .email(request.getEmail())
                .build());
        return UserTokenDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .token(token)
                .build();
    }



}
