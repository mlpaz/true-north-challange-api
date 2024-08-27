package com.test.demo.api.service;

import com.test.demo.api.adapter.persistence.UserJPARepository;
import com.test.demo.api.exceptions.ResourceNotFoundException;
import com.test.demo.api.model.User;
import com.test.demo.api.model.enums.OperationType;
import com.test.demo.api.model.enums.Status;
import com.test.demo.api.resource.dto.UserDetailsDTO;
import com.test.demo.api.resource.request.operation.OperationRequest;
import com.test.demo.api.resource.request.operation.input.IOperationInput;
import com.test.demo.api.service.operation.AbstractOperation;
import com.test.demo.api.service.operation.OperationMap;
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
