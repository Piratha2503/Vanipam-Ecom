package com.service.Inventory.Utils;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class DateTimeUtils {
    @CreationTimestamp
    private Timestamp created_timestamp;
    @UpdateTimestamp
    private Timestamp updated_timestamp;
}
