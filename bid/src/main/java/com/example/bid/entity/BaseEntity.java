package com.example.bid.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;

    @Column(updatable = false)
    @JsonIgnore
    @CreationTimestamp
    private Timestamp createdOn = new Timestamp(new Date().getTime());

    @UpdateTimestamp
    @JsonIgnore
    private Timestamp updatedOn = new Timestamp(new Date().getTime());

    private Boolean isDeleted = Boolean.FALSE;

    private Boolean isActive = Boolean.TRUE;

    @Column(columnDefinition = "text")
    private String searchKey;

}