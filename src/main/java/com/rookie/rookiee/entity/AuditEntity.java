package com.rookie.rookiee.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class AuditEntity<P extends Serializable> implements Persistable<P> {

    @Column(name = "DATE_CREATED")
    @CreatedDate
    LocalDateTime dateCreated;

    @Column(name = "DATE_MODIFIED")
    @LastModifiedDate
    LocalDateTime dateModified;

    @Column(name = "MODIFIED_BY")
    @LastModifiedBy
    String modifiedUser;

}
