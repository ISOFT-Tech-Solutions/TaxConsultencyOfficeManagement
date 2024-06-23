package com.isoft.mtax.service;

import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    TDSCustomer save(TDSCustomer tdsCustomer);



    List<TDSCustomer> tdsCustomers();

    TDSCustomer tdsCustomerBasedOnTanNumber(String tanNumber);

    List<Map<String, Object>> findTdsCustomerByAddressCity(String city);

    TDSCustomer updateTDSCustomer(Long id, TDSCustomer updatedTDSCustomer);

    TDSCustomer deactivateTdsCustomer(Long id);

    TDSCustomer restoreTdsCustomer(Long id);

    GSTCustomer addGstCustomer(GSTCustomer gstCustomer);

    Page<GSTCustomer> gstCustomers(int page, int size);

    GSTCustomer gstCustomerbasedOnGstinNumber(String gstinNumber);

    GSTCustomer updateGstCustomer(Long id, GSTCustomer customer);
}
