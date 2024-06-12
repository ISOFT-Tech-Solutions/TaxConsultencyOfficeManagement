package com.isoft.mtax.service;

import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;

import java.util.List;

public interface CustomerService {
    TDSCustomer addTDSCustomer(TDSCustomer tdsCustomer);



    List<TDSCustomer> tdsCustomers();

    TDSCustomer tdsCustomerBasedOnTanNumber(String tanNumber);

    List<TDSCustomer> findTdsCustomerByAddressCity(String city);

    TDSCustomer updateTDSCustomer(Long id, TDSCustomer updatedTDSCustomer);

    TDSCustomer deactivateTdsCustomer(Long id);

    TDSCustomer restoreTdsCustomer(Long id);

    GSTCustomer addGstCustomer(GSTCustomer gstCustomer);
}
