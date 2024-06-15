package com.isoft.mtax.listnear;

import com.isoft.mtax.dto.Auditable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditListener {
    @PrePersist
    public void setCreatedOn(Auditable auditable) {
        auditable.setCreatedDate(LocalDateTime.now());
        auditable.setCreatedBy(getCurrentAuditor());
    }

    @PreUpdate
    public void setUpdatedOn(Auditable auditable) {
        auditable.setLastModifiedDate(LocalDateTime.now());
        auditable.setLastModifiedBy(getCurrentAuditor());
    }

    private String getCurrentAuditor() {
        // This can be customized to fetch from the security context
        return "system"; // Placeholder for the current user
    }
}
