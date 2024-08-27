package com.test.demo.api.adapter.persistence;

import com.test.demo.api.model.Operation;
import com.test.demo.api.model.User;
import com.test.demo.api.model.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface OperationJPARepository extends JpaRepository<Operation, UUID> {

    Optional<Operation> findByType(OperationType type);

}
