package com.isoft.mtax.repo;


import com.isoft.mtax.entity.TDSCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepo extends JpaRepository<TDSCustomer,Long> {
    TDSCustomer findByTanNumber(String tanNumber);
    @Query(value = "SELECT tc.* FROM tds_customer tc JOIN Address a ON tc.address_id = a.id WHERE LOWER(a.city) = LOWER(:city)", nativeQuery = true)
    List<TDSCustomer> findByAddress_City(String city);
}
