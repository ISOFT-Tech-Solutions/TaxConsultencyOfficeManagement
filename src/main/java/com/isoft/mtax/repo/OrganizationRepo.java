package com.isoft.mtax.repo;

import com.isoft.mtax.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization,Long> {
}
