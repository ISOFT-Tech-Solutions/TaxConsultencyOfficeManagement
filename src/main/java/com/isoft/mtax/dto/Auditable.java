package com.isoft.mtax.dto;

import com.isoft.mtax.listnear.AuditListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Data

@EntityListeners(AuditListener.class)

@NoArgsConstructor
@AllArgsConstructor

public abstract class Auditable<U> {
    @Column(name = "created_by")
    private U createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_by")
    private U lastModifiedBy;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;



}
