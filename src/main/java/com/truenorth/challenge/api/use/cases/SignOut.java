package com.truenorth.challenge.api.use.cases;

import com.truenorth.challenge.api.adapter.persistence.UserJPARepository;
import com.truenorth.challenge.api.exceptions.ResourceNotFoundException;
import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.model.enums.Status;
import com.truenorth.challenge.api.port.in.SignOutCommand;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
