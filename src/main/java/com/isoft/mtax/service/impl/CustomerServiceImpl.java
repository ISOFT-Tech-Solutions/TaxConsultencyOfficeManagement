package com.isoft.mtax.service.impl;

import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.repo.CustomerRepo;
import com.isoft.mtax.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;
    @Override
    public TDSCustomer addTDSCustomer(TDSCustomer tdsCustomer) {
        return customerRepo.save(tdsCustomer);
    }

    @Override
    public List<TDSCustomer> tdsCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public TDSCustomer tdsCustomerBasedOnTanNumber(String tanNumber) {

        return customerRepo.findByTanNumber(tanNumber);
    }

    @Override
    public List<TDSCustomer> findByAddress_City(String city) {

        return customerRepo.findByAddress_City(city);
    }
}
