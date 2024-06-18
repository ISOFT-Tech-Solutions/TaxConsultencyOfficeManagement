package com.isoft.mtax.controller;

import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
      TDSCustomer customer =customerService.save(tdsCustomer);
      return new ResponseEntity<>(customer.getCustomerName()+"Added Succussfully and Email Send", HttpStatus.CREATED);
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
            List<Map<String, Object>> tdsCustomersByCity =customerService.findTdsCustomerByAddressCity(city);
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
        return new ResponseEntity<>("TDS Customer : "+tdsCustomer.getCustomerName()+" Deactivated Succussfully",HttpStatus.OK);
    }
    @PutMapping("/restore/tds-customers/{id}")
    public ResponseEntity<?> restoreTdsCustomer(@PathVariable Long id){
        TDSCustomer tdsCustomer=customerService.restoreTdsCustomer(id);
        return new ResponseEntity<>("TDS Customer : "+tdsCustomer.getCustomerName()+" Restored  Succussfully",HttpStatus.OK);
    }

    /**
     * Added GST Customer
     * @param gstCustomer
     * @return GST
     */
    @PostMapping("/gst-customers")
    public ResponseEntity<?> addGSTCustomer(@RequestBody GSTCustomer gstCustomer){
        GSTCustomer addedGstCustomer =customerService.addGstCustomer(gstCustomer);
        return new ResponseEntity<>(addedGstCustomer.getCustomerName()+"Added Succussfully and Email Send", HttpStatus.CREATED);
    }

    /**
     * Get All GST Customer using Pagingnation
     * based on Page and Size
     * @param page
     * @param size
     * @return GST Customer List
     */
    @GetMapping("/gst-customers")
    public ResponseEntity<?> allGstCustomers(
            @RequestParam (defaultValue = "0")int page,
            @RequestParam (defaultValue = "10") int size){
        Page<GSTCustomer> gstCustomers=customerService.gstCustomers(page,size);
        return new ResponseEntity<>(gstCustomers,HttpStatus.OK);

    }

    /**
     * Get GST Customer based on GSTIN Number
     * @param gstinNumber
     * @return GST Customer
     */

    @GetMapping("/gst-customers/{gstin-number}")
    public ResponseEntity<?> gstCustomerbasedOnGstinNumber(@PathVariable("gstin-number") String gstinNumber){
        log.info("GSTIN Number :"+gstinNumber);
        GSTCustomer gstCustomer=customerService.gstCustomerbasedOnGstinNumber(gstinNumber);
        if(gstCustomer ==null){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(gstCustomer);
    }
    @PutMapping("/gst-customers/{id}")
    public ResponseEntity<?> updateGstCustomer(@PathVariable Long id,@RequestBody GSTCustomer customer){
        GSTCustomer updatedGstCustomer = customerService.updateGstCustomer(id,customer);
        return new ResponseEntity<>(updatedGstCustomer.getCustomerName() +" : updated Succussfully",HttpStatus.OK);
    }

}
