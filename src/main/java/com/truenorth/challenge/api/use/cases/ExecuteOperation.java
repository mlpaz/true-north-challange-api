package com.truenorth.challenge.api.use.cases;

import com.truenorth.challenge.api.adapter.persistence.OperationJPARepository;
import com.truenorth.challenge.api.adapter.persistence.RecordJPARepository;
import com.truenorth.challenge.api.adapter.persistence.UserJPARepository;
import com.truenorth.challenge.api.exceptions.OperationNotAcceptableException;
import com.truenorth.challenge.api.exceptions.ResourceNotFoundException;
import com.truenorth.challenge.api.factory.RecordDTOFactory;
import com.truenorth.challenge.api.model.Operation;
import com.truenorth.challenge.api.model.Record;
import com.truenorth.challenge.api.model.User;
import com.truenorth.challenge.api.resource.dto.RecordDTO;
import com.truenorth.challenge.api.port.in.ExecuteOperationCommand;
import com.truenorth.challenge.api.resource.request.operation.OperationRequest;
import com.truenorth.challenge.api.service.OperationService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@Slf4j
@Transactional
public class ExecuteOperation implements ExecuteOperationCommand {

    @Autowired
    OperationJPARepository operationRepository;

    @Autowired
    RecordJPARepository recordRepository;

    @Autowired
    UserJPARepository userRepository;

    @Autowired
    OperationService operationService;

    @Override
    public RecordDTO execute(OperationRequest request) {

        log.info("Try to find operation type {}", request.getOperationType());
        Operation operation = operationRepository.findByType(request.getOperationType())
                .orElseThrow(() -> new ResourceNotFoundException("Operation Not Found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));


        log.info("Execute {} operation with cost {}", request.getOperationType(), operation.getCost());
        String operationResponse = operationService.executeOperation(request);


        BigDecimal userBalance = user.getCredit().subtract(operation.getCost());
        if (BigDecimal.ZERO.compareTo(userBalance) > 0){
            throw new OperationNotAcceptableException("Operation Amount " + operation.getCost() +", you have insufficient Credit.");
        }
        user.setCredit(userBalance);
        userRepository.save(user);
        log.info("Calculate and update new user credit balance {}", userBalance);

        Record record = Record.builder()
                .operation(operation)
                .userId(user.getId())
                .operationResponse(operationResponse)
                .userBalance(userBalance)
                .amount(operation.getCost())
                .build();
        Record saveRecord = recordRepository.save(record);

        return RecordDTOFactory.fromModel(saveRecord);
    }
}
