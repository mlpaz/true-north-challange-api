package com.truenorth.challenge.api.adapter.persistence;

import com.truenorth.challenge.api.model.Record;
import com.truenorth.challenge.api.model.enums.OperationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface RecordJPARepository extends JpaRepository<Record, UUID>, JpaSpecificationExecutor<Record> {

    @Query(value = "SELECT r FROM Record r " +
            "JOIN  r.operation o " +
            "WHERE r.userId = :userId AND r.deletedAt IS NULL AND o.type = :operationType " +
            "ORDER BY createdAt")
    Page<Record> findPageByUserId(@Param("userId") UUID userId, OperationType operationType, Pageable page);

}
