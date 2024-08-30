package com.truenorth.challenge.api.service;

import com.truenorth.challenge.api.adapter.persistence.UserJPARepository;
import com.truenorth.challenge.api.exceptions.ResourceNotFoundException;
import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.model.enums.Status;
import com.truenorth.challenge.api.resource.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserJPARepository repository;

    public UserDetails loadUserByEmail(String email){
        User user = repository.findFirstByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return  UserDetailsDTO.builder()
                .active(Status.ACTIVE.equals(user.getStatus()))
                .email(user.getEmail())
                .build();
    }


}
