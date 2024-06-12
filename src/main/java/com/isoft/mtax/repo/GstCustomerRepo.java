package com.isoft.mtax.repo;

import com.isoft.mtax.entity.GSTCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GstCustomerRepo extends JpaRepository<GSTCustomer,Long> {
}
