package com.isoft.mtax.service;

import com.isoft.mtax.entity.TDSCustomer;

import java.util.List;

public interface CustomerService {
    TDSCustomer addTDSCustomer(TDSCustomer tdsCustomer);



    List<TDSCustomer> tdsCustomers();

    TDSCustomer tdsCustomerBasedOnTanNumber(String tanNumber);

    List<TDSCustomer> findByAddress_City(String city);
}
