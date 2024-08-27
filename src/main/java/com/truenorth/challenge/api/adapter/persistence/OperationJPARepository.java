package com.truenorth.challenge.api.adapter.persistence;

import com.truenorth.challenge.api.model.Operation;
import com.truenorth.challenge.api.model.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface OperationJPARepository extends JpaRepository<Operation, UUID> {

    Optional<Operation> findByType(OperationType type);

}
