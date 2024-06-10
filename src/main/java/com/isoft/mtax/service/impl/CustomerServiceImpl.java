package com.isoft.mtax.service.impl;

import com.isoft.mtax.dto.EmailDetails;
import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.exception.ResourceNotFoundException;
import com.isoft.mtax.repo.CustomerRepo;
import com.isoft.mtax.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    KafkaProducerService kafkaProducerService;

    @Override
    @Transactional
    public TDSCustomer addTDSCustomer(TDSCustomer tdsCustomer) {
        TDSCustomer addedTdsCustomer=customerRepo.save(tdsCustomer);

         sendNewCustomerNotification(addedTdsCustomer);
        return addedTdsCustomer;
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
    public List<TDSCustomer> findTdsCustomerByAddressCity(String city) {

        return customerRepo.findTdsCustomerByCity(city);
    }

    @Override
    @Transactional
    public TDSCustomer updateTDSCustomer(Long id, TDSCustomer updatedTDSCustomer) {
        return customerRepo.findById(id)
                .map(tdsCustomer -> {
                    tdsCustomer.setTdsCustomerName(updatedTDSCustomer.getTdsCustomerName());
                    tdsCustomer.setMobile(updatedTDSCustomer.getMobile());
                    tdsCustomer.setPan(updatedTDSCustomer.getPan());
                    tdsCustomer.setEmail(updatedTDSCustomer.getEmail());
                    return customerRepo.save(tdsCustomer);
                }).orElseThrow(()-> new ResourceNotFoundException("TDS Customer Not found with id : "+id));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TDSCustomer deactivateTdsCustomer(Long id) {
        TDSCustomer tdsCustomer=customerRepo.findById(id).orElseThrow(() ->new ResourceNotFoundException("TDS Customer not found with id : "+id));
        tdsCustomer.setActive(false);
        customerRepo.save(tdsCustomer);
        return tdsCustomer;
    }

    /**
     * @param id
     * @return TDS Customer
     */
    @Override
    public TDSCustomer restoreTdsCustomer(Long id) {
        TDSCustomer tdsCustomer=customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("TDS Customer not found with id : "+id));
        tdsCustomer.setActive(true);
        customerRepo.save(tdsCustomer);
        return tdsCustomer;
    }

    /**
     * 
     * @param tdsCustomer
     */
    private  void sendNewCustomerNotification(TDSCustomer tdsCustomer) {
        EmailDetails emailDetails=new EmailDetails();
        emailDetails.setTo(tdsCustomer.getEmail()); // Email recipient

        String text = "We are delighted to welcome +"+tdsCustomer.getTdsCustomerName()+" to Maa Mundeswari Tax Consultancy \n\n" +
                "Thank you for choosing us as your trusted partner for tax consultancy services.";

        emailDetails.setSubject("Welcome to Maa Mundeswari Tax Consultancy – Your Trusted Tax Consultancy Partner");
        emailDetails.setBody(text);
        kafkaProducerService.sendMessage(emailDetails);


    }
}
