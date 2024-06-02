package com.isoft.mtax.repo;


import com.isoft.mtax.entity.TDSCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<TDSCustomer,Long> {
}
