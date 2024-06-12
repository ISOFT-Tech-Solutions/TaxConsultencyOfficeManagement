package com.isoft.mtax.service.impl;

import com.isoft.mtax.dto.EmailDetails;
import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.exception.ResourceNotFoundException;
import com.isoft.mtax.repo.GstCustomerRepo;
import com.isoft.mtax.repo.TdsCustomerRepo;
import com.isoft.mtax.service.CustomerService;
import com.isoft.mtax.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private TdsCustomerRepo tdsCustomerRepo;
    @Autowired
    private MailService mailService;
    @Autowired
    private GstCustomerRepo gstCustomerRepo;

    @Override
    @Transactional
    public TDSCustomer addTDSCustomer(TDSCustomer tdsCustomer) {
        TDSCustomer addedTdsCustomer= tdsCustomerRepo.save(tdsCustomer);

         mailService.sendEmailNotification(addedTdsCustomer);
        return addedTdsCustomer;
    }

    @Override
    public List<TDSCustomer> tdsCustomers() {
        return tdsCustomerRepo.findAll();
    }

    @Override
    public TDSCustomer tdsCustomerBasedOnTanNumber(String tanNumber) {

        return tdsCustomerRepo.findByTanNumber(tanNumber);
    }

    @Override
    public List<TDSCustomer> findTdsCustomerByAddressCity(String city) {

        return tdsCustomerRepo.findTdsCustomerByCity(city);
    }

    @Override
    @Transactional
    public TDSCustomer updateTDSCustomer(Long id, TDSCustomer updatedTDSCustomer) {
        return tdsCustomerRepo.findById(id)
                .map(tdsCustomer -> {
                    tdsCustomer.setCustomerName(updatedTDSCustomer.getCustomerName());
                    tdsCustomer.setMobile(updatedTDSCustomer.getMobile());
                    tdsCustomer.setPan(updatedTDSCustomer.getPan());
                    tdsCustomer.setEmail(updatedTDSCustomer.getEmail());
                    return tdsCustomerRepo.save(tdsCustomer);
                }).orElseThrow(()-> new ResourceNotFoundException("TDS Customer Not found with id : "+id));
    }

    /**
     * Deactivate TDS Customer
     * @param id
     * @return
     */
    @Override
    public TDSCustomer deactivateTdsCustomer(Long id) {
        TDSCustomer tdsCustomer= tdsCustomerRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("TDS Customer not found with id : "+id));
        tdsCustomer.setActive(false);
        tdsCustomerRepo.save(tdsCustomer);
        return tdsCustomer;
    }

    /**
     * Restore TDS Customer
     * @param id
     * @return TDS Customer
     */
    @Override
    public TDSCustomer restoreTdsCustomer(Long id) {
        TDSCustomer tdsCustomer= tdsCustomerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("TDS Customer not found with id : "+id));
        tdsCustomer.setActive(true);
        tdsCustomerRepo.save(tdsCustomer);
        return tdsCustomer;
    }

    /**
     * Add GST Customer
     * @param gstCustomer
     * @return GSTCustomer
     */
    @Transactional
    @Override
    public GSTCustomer addGstCustomer(GSTCustomer gstCustomer) {
        GSTCustomer addedGstCustomer =gstCustomerRepo.save(gstCustomer);
        mailService.sendEmailNotification(addedGstCustomer);
        return gstCustomerRepo.save(gstCustomer);
    }

}
