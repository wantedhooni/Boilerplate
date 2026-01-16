package com.revy.api_server.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@MappedSuperclass
@ToString
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private T id;

//    @Column(name="publicId", nullable = false, unique = true, columnDefinition = "UUID")
//    private UUID publicId;         // UUID v7 (외부 노출)
//
//    @PrePersist
//    void prePersist() {
//        if (this.publicId == null) {
//            this.publicId = UuidCreator.getTimeOrderedEpoch();
//        }
//    }

//    @CreatedBy
//    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
//    private String createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

//    @LastModifiedBy
//    @Column(name = "last_modified_by", length = 50)
//    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();
}
