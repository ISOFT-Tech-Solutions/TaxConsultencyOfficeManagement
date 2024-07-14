package com.isoft.mtax.repo;


import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface   CustomerRepo extends JpaRepository<Customer,Long> {
    TDSCustomer findByTanNumber(String tanNumber);
    @Query(value = "select c.id,c.customer_name ,c.email ,c.pan ,c.mobile,c.created_by,c.created_date ,c.phone_no,c.last_modified_by ,c.last_modified_date  from customer c join tds_customer tc on c.id=tc.id join address a on tc.address_id =a.id where  LOWER(a.city)  = LOWER(:city)", nativeQuery = true)
    List<Map<String, Object>> findTdsCustomerByCity(String city);
    @Query("SELECT c FROM GSTCustomer c")
    Page<GSTCustomer> findAllGstCustomer(Pageable pageable);
    @Query("Select t from TDSCustomer t")
    Page<TDSCustomer> findAllTdsCustomers(Pageable pageable);
}
