package com.isoft.mtax.service.impl;

import com.isoft.mtax.dto.GstCustomerDto;
import com.isoft.mtax.dto.TdsCustomerDto;
import com.isoft.mtax.entity.Address;
import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.exception.ResourceNotFoundException;
import com.isoft.mtax.mapper.CustomerMapper;
import com.isoft.mtax.repo.CustomerRepo;
import com.isoft.mtax.service.CustomerService;
import com.isoft.mtax.service.MailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private MailService mailService;


    @Override
    @Transactional
    public Customer save(Customer customer) {
        Customer addedCustomer= customerRepo.save(customer);

         /*mailService.sendEmailNotification(addedTdsCustomer);*/
        return addedCustomer;
    }

    @Override
    public List<Customer> tdsCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public TDSCustomer tdsCustomerBasedOnTanNumber(String tanNumber) {

        return customerRepo.findByTanNumber(tanNumber);
    }

    @Override
    public List<Map<String, Object>> findTdsCustomerByAddressCity(String city) {
       List<Map<String, Object>> list =customerRepo.findTdsCustomerByCity(city);

        return list;
    }

    @Override
    public Customer updateTDSCustomer(Long id, TDSCustomer updatedTDSCustomer) {
        return null;
    }


   /* @Transactional
    public Customer updateTDSCustomer(Long id, Customer updatedCustomer) {
        return customerRepo.findById(id)
                .map(customer -> {
                    customer.setCustomerName(updatedCustomer.getCustomerName());
                    customer.setMobile(updatedCustomer.getMobile());
                    customer.setPan(updatedCustomer.getPan());
                    customer.setEmail(updatedCustomer.getEmail());
                    return customerRepo.save(customer);
                }).orElseThrow(()-> new ResourceNotFoundException("TDS Customer Not found with id : "+id));
    }
*/
    @Override
    public Customer deactivateTdsCustomer(Long id) {
        return null;
    }

    @Override
    public Customer restoreTdsCustomer(Long id) {
        return null;
    }

    /**
     * Deactivate TDS Customer
     * @param id
     * @return
     */

    public Customer deactivateCustomer(Long id) {
        Customer customer= customerRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("TDS Customer not found with id : "+id));
        customer.setActive(false);
        customerRepo.save(customer);
        return customer;
    }

    /**
     * Restore TDS Customer
     * @param id
     * @return TDS Customer
     */

    public Customer restoreCustomer(Long id) {
        Customer customer= customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("TDS Customer not found with id : "+id));
        customer.setActive(true);
        customerRepo.save(customer);
        return customer;
    }


    @Override
    public Page<GSTCustomer> gstCustomers(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return customerRepo.findAllGstCustomer(pageable);
    }

   /* public GSTCustomer gstCustomerbasedOnGstinNumber(String gstinNumber) {
        return gstCustomerRepo.findByGstinNumber(gstinNumber);
    }*/


    public GSTCustomer updateGstCustomer(Long id, GSTCustomer customer) {
       return (GSTCustomer) customerRepo.findById(id)
                .map(gstCustomer -> {
                    gstCustomer.setCustomerName(customer.getCustomerName());
                    gstCustomer.setMobile(customer.getMobile());
                    gstCustomer.setPan(customer.getPan());
                    gstCustomer.setEmail(customer.getEmail());
                    return customerRepo.save(gstCustomer);
                }).orElseThrow(()-> new ResourceNotFoundException("GST Customer Not found with id : "+id));


    }

    @Transactional
    public TDSCustomer saveCsvTdsCustomer(TdsCustomerDto tdsCustomerDto) {
        TDSCustomer tdsCustomer=CustomerMapper.toEntity(tdsCustomerDto);
        TDSCustomer customer =customerRepo.save(tdsCustomer);
        return customer;
    }

    public Customer customersDetails(Long id) {
        return customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer Not Found"));
    }

    @Override

    public Page<TDSCustomer> findAllTdsCustomers(Pageable pageable) {

        return customerRepo.findAllTdsCustomers(pageable);
    }


    @Transactional
    public void saveCsvGstCustomer(GstCustomerDto gstCustomerDto) {
        GSTCustomer gstCustomer= CustomerMapper.toEntity(gstCustomerDto);
        customerRepo.save(gstCustomer);
    }
    public Customer tdsCustomersDetails(Long id) {
        return customerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("TDS Customer Not Found"));
    }

}
