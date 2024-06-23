package com.isoft.mtax.service.impl;

import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.exception.ResourceNotFoundException;
import com.isoft.mtax.repo.GstCustomerRepo;
import com.isoft.mtax.repo.TdsCustomerRepo;
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
    private TdsCustomerRepo tdsCustomerRepo;
    @Autowired
    private MailService mailService;
    @Autowired
    private GstCustomerRepo gstCustomerRepo;

    @Override
    @Transactional
    public TDSCustomer save(TDSCustomer tdsCustomer) {
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
    public List<Map<String, Object>> findTdsCustomerByAddressCity(String city) {
       List<Map<String, Object>> list =tdsCustomerRepo.findTdsCustomerByCity(city);
       log.info("List Of Size"+list.size());
        return list;
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

    @Override
    public Page<GSTCustomer> gstCustomers(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return gstCustomerRepo.findAll(pageable);
    }

    @Override
    public GSTCustomer gstCustomerbasedOnGstinNumber(String gstinNumber) {
        return gstCustomerRepo.findByGstinNumber(gstinNumber);
    }

    @Override
    public GSTCustomer updateGstCustomer(Long id, GSTCustomer customer) {
        return gstCustomerRepo.findById(id)
                .map(gstCustomer -> {
                    gstCustomer.setCustomerName(customer.getCustomerName());
                    gstCustomer.setMobile(customer.getMobile());
                    gstCustomer.setPan(customer.getPan());
                    gstCustomer.setEmail(customer.getEmail());
                    return gstCustomerRepo.save(gstCustomer);
                }).orElseThrow(()-> new ResourceNotFoundException("GST Customer Not found with id : "+id));

    }

}
