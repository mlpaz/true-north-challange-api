package com.test.demo.api.use.cases;

import com.test.demo.api.adapter.persistence.OperationJPARepository;
import com.test.demo.api.adapter.persistence.RecordJPARepository;
import com.test.demo.api.adapter.persistence.UserJPARepository;
import com.test.demo.api.exceptions.ResourceNotFoundException;
import com.test.demo.api.factory.RecordDTOFactory;
import com.test.demo.api.model.Operation;
import com.test.demo.api.model.Record;
import com.test.demo.api.model.User;
import com.test.demo.api.resource.dto.RecordDTO;
import com.test.demo.api.port.in.ExecuteOperationCommand;
import com.test.demo.api.resource.request.operation.OperationRequest;
import com.test.demo.api.service.OperationService;
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
        user.setCredit(userBalance);
        userRepository.save(user);
        log.info("Calculate and update new user credit balance {}", userBalance);

        Record record = Record.builder()
                .operation(operation)
                .userId(user.getId())
                .operationResponse(operationResponse)
                .userBalance(userBalance)
                .build();
        Record saveRecord = recordRepository.save(record);

        return RecordDTOFactory.fromModel(saveRecord);
    }
}
