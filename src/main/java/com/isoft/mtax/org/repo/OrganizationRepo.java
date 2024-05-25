package com.isoft.mtax.org.repo;

import com.isoft.mtax.org.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Long, Organization> {
}
