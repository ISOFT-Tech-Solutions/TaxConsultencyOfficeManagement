package com.isoft.mtax.controller;

import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mtax")
public class CustomerController {
    private static final Logger LOGGER= LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerService customerService;

    /**
     * Add TDS Customer
     * @param tdsCustomer
     * @return Added Customer Detail with Status Created
     */
    @PostMapping("/tds-customers")
    public ResponseEntity<?> addTDSCustomer(@RequestBody TDSCustomer tdsCustomer){
      TDSCustomer customer =customerService.addTDSCustomer(tdsCustomer);
      return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    /**
     * Search all TDS Customer
     * @return All TDS Customer List Data
     */
    @GetMapping ("/tds-customers")
    public ResponseEntity<?> tdsCustomers(){
        List<TDSCustomer> tdsCustomerList=customerService.tdsCustomers();
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

        TDSCustomer tdsCustomer=customerService.tdsCustomerBasedOnTanNumber(tanNumber);
        if(tdsCustomer==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tdsCustomer);

    }

    /**
     * Search TDS Customer based on City name
     * @param city It is case insensetive
     * @return TDS Customer list based on City
     *   or an HTTP status code 204 (No Content) if no users are found
     */

    @GetMapping("/tds-customers/search")
    public  ResponseEntity<?> searchTdsCustomerByCity(@RequestParam String city){
        LOGGER.info("City Name"+city);
        System.out.println("searchTdsCustomerByCity Method ");
        System.out.println("city name "+city);
        List<TDSCustomer> tdsCustomersByCity =customerService.findByAddress_City(city);
        LOGGER.info("Customer List"+tdsCustomersByCity.size());
        if(tdsCustomersByCity.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tdsCustomersByCity);
    }
}
