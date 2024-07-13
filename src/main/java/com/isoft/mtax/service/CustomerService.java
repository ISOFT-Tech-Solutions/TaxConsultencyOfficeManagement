package com.isoft.mtax.service;

import com.isoft.mtax.dto.GstCustomerDto;
import com.isoft.mtax.dto.TdsCustomerDto;
import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    Customer save(Customer customer);



    List<Customer> tdsCustomers();

    TDSCustomer tdsCustomerBasedOnTanNumber(String tanNumber);

    List<Map<String, Object>> findTdsCustomerByAddressCity(String city);

    Customer updateTDSCustomer(Long id, TDSCustomer updatedTDSCustomer);

    Customer deactivateTdsCustomer(Long id);

    Customer restoreTdsCustomer(Long id);

   /* GSTCustomer addGstCustomer(GSTCustomer gstCustomer);*/

  Page<GSTCustomer> gstCustomers(int page, int size);

  /*  GSTCustomer gstCustomerbasedOnGstinNumber(String gstinNumber);*/

   /* GSTCustomer updateGstCustomer(Long id, GSTCustomer customer);*/

    TDSCustomer saveCsvTdsCustomer(TdsCustomerDto tdsCustomerDto);

 /*   Optional<GSTCustomer> gstCustomerDetails(Long id);*/

    Customer customersDetails(Long id);

    Page<TDSCustomer> findAllTdsCustomers(Pageable pageable);

    void saveCsvGstCustomer(GstCustomerDto gstCustomer);
}
