package com.truenorth.challenge.api.model;

import com.truenorth.challenge.api.model.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "operation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    UUID id;
    @Enumerated(EnumType.STRING)
    OperationType type;

    private BigDecimal cost;

}
