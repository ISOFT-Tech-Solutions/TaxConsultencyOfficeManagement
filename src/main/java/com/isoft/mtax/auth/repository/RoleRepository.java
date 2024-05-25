package com.isoft.mtax.auth.repository;

import java.util.Optional;

import com.isoft.mtax.auth.models.ERole;
import com.isoft.mtax.auth.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
