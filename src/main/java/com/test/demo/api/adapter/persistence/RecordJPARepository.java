package com.test.demo.api.adapter.persistence;

import com.test.demo.api.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface RecordJPARepository extends JpaRepository<Record, UUID> {

    @Query(value = "SELECT r FROM Record r " +
            "WHERE r.userId = :userId and r.deletedAt IS NULL  " +
            "ORDER BY createdAt")
    Page<Record> findPageByUserId(@Param("userId") UUID userId, Pageable page);

}
