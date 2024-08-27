package com.truenorth.challenge.api.adapter.persistence.specification;

import com.truenorth.challenge.api.model.Operation;
import com.truenorth.challenge.api.model.Record;
import com.truenorth.challenge.api.model.enums.OperationType;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;
import java.util.UUID;

public class RecordSpecifications {
    public static Specification<Record> eqType(OperationType type) {
        return (root, query, criteriaBuilder) -> {
            Join<Record, Operation> operation = root.join("operation");
            return  criteriaBuilder.equal(operation.get("type"), type);


        };
    }
    public static Specification<Record> eqUser(UUID id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), id);

    }
    public static Specification<Record> notDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("deletedAt"));
    }
    public static Specification<Record> greaterThanDate(OffsetDateTime greaterThanDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), greaterThanDate);
    }

    public static Specification<Record> lessThanDate(OffsetDateTime lessThanDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), lessThanDate);
    }
}
