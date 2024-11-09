package com.threadsafe.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private Instant createdDate;

    @CreatedBy
    @Column(updatable = false, nullable = false)
    @JsonIgnore
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    @JsonIgnore
    private Instant LastModifiedDate;

    @LastModifiedBy
    @Column(insertable = false)
    @JsonIgnore
    private String LastModifiedBy;
}
