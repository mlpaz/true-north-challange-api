package com.truenorth.challenge.api.use.cases;

import com.truenorth.challenge.api.adapter.persistence.UserJPARepository;
import com.truenorth.challenge.api.exceptions.UnauthorizedException;
import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.model.enums.Status;
import com.truenorth.challenge.api.port.in.LogInCommand;
import com.truenorth.challenge.api.resource.dto.UserDetailsDTO;
import com.truenorth.challenge.api.resource.dto.UserTokenDTO;
import com.truenorth.challenge.api.resource.request.LogInRequest;
import com.truenorth.challenge.api.service.jwt.JwtService;
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
                .credit(user.getCredit())
                .token(token)
                .build();
    }



}
