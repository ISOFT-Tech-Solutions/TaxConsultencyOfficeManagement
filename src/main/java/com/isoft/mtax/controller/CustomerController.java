package com.isoft.mtax.controller;

import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/mtax")
@Validated
@Log4j2

public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * Added  TDS Customer
     * @param tdsCustomer
     * @return Added Customer Detail with Status Created
     */
    @PostMapping("/tds-customers")
    public ResponseEntity<?> addTDSCustomer(@Valid  @RequestBody TDSCustomer tdsCustomer){
      TDSCustomer customer =customerService.addTDSCustomer(tdsCustomer);
      return new ResponseEntity<>(customer.getTdsCustomerName()+"Added Succussfully and Email Send", HttpStatus.CREATED);
    }

    /**
     * Search all TDS Customer
     * Search Customer based on City if City Request Param will be provided
     * @return TDS Customer List Data
     */
    @GetMapping ("/tds-customers")
    public ResponseEntity<?> tdsCustomers(@RequestParam(required = false) String city){
        log.info("tds customer "+city);
        List<TDSCustomer> tdsCustomerList=new ArrayList<>();
        if(city!=null){
            List<TDSCustomer> tdsCustomersByCity =customerService.findTdsCustomerByAddressCity(city);
            return ResponseEntity.ok(tdsCustomersByCity);
        }
        else {
            log.info("In else condition");
            tdsCustomerList=customerService.tdsCustomers();
        }
        if(tdsCustomerList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tdsCustomerList);
    }

    /**
     * Serach user based on tanNumber
     * @param tanNumber
     * @return TDSCustomer based on search cretria
     *   or an HTTP status code 204 (No Content) if no users are found
     */
    @GetMapping("/tds-customers/{tan-number}")
    public ResponseEntity<?> tdsCustomerBasedOnTanNumber(@PathVariable("tan-number") String tanNumber) {
        log.info("tanNumber   "+tanNumber);

        TDSCustomer tdsCustomer=customerService.tdsCustomerBasedOnTanNumber(tanNumber);
        if(tdsCustomer==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tdsCustomer);

    }

    /**
     * Update TDS Customer
     * @param id
     * @return
     */
    @PutMapping("/tds-customers/{id}")
    public ResponseEntity<?> updateTdsCustomer(@PathVariable Long id, @RequestBody TDSCustomer updatedTDSCustomer){
        TDSCustomer updatedTdsCustomer =customerService.updateTDSCustomer(id,updatedTDSCustomer);
        return new  ResponseEntity<>(updatedTdsCustomer,HttpStatus.OK);


    }
    @DeleteMapping("/tds-customers/{id}")
    public ResponseEntity<?> deactivateTdsCustomer(@PathVariable Long id){
        TDSCustomer tdsCustomer=customerService.deactivateTdsCustomer(id);
        return new ResponseEntity<>("TDS Customer : "+tdsCustomer.getTdsCustomerName()+" Deactivated Succussfully",HttpStatus.OK);
    }
    @PutMapping("/restore/tds-customers/{id}")
    public ResponseEntity<?> restoreTdsCustomer(@PathVariable Long id){
        TDSCustomer tdsCustomer=customerService.restoreTdsCustomer(id);
        return new ResponseEntity<>("TDS Customer : "+tdsCustomer.getTdsCustomerName()+" Restored  Succussfully",HttpStatus.OK);
    }

}
