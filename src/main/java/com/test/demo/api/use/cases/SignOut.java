package com.test.demo.api.use.cases;

import com.test.demo.api.adapter.persistence.UserJPARepository;
import com.test.demo.api.exceptions.ResourceNotFoundException;
import com.test.demo.api.exceptions.UnauthorizedException;
import com.test.demo.api.model.User;
import com.test.demo.api.model.enums.Status;
import com.test.demo.api.port.in.LogInCommand;
import com.test.demo.api.port.in.SignOutCommand;
import com.test.demo.api.resource.dto.UserDetailsDTO;
import com.test.demo.api.resource.dto.UserTokenDTO;
import com.test.demo.api.resource.request.LogInRequest;
import com.test.demo.api.service.jwt.JwtService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@Transactional
public class SignOut implements SignOutCommand {

    @Autowired
    UserJPARepository repository;



    @Override
    public void execute() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        log.info("Try tu set INACTIVE  status to {} user", email);
        User user = repository.findFirstByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        user.setStatus(Status.INACTIVE);
        repository.save(user);

    }



}
