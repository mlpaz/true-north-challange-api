package com.test.demo.api.model;


import com.test.demo.api.model.enums.Status;
import com.test.demo.api.model.transformation.AttributeEncryptor;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    UUID id;

    String email;

    @Convert(converter = AttributeEncryptor.class)
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    private BigDecimal credit;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    @PreRemove
    protected void onDelete() {
        deletedAt = OffsetDateTime.now();
    }

}
