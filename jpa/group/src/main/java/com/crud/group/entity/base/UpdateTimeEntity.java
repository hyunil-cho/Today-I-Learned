package com.crud.group.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class UpdateTimeEntity extends CreateTimeEntity{
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
