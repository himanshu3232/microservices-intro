package com.microservies.intro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @ToString
public class BaseEntity {
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;
    @Column(name="updated_at", insertable = false)
    private LocalDateTime updatedAt;
}
