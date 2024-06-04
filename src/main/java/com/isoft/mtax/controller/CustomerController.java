package com.isoft.mtax.controller;

import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mtax")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/tds-customers")
    public ResponseEntity<?> addTDSCustomer(@RequestBody TDSCustomer tdsCustomer){
      TDSCustomer customer =customerService.addTDSCustomer(tdsCustomer);
      return new ResponseEntity<>(customer, HttpStatusCode.valueOf(200));
    }
    @GetMapping ("/tds-customers")
    ResponseEntity<?> tdsCustomers(){
        List<TDSCustomer> tdsCustomerList=customerService.tdsCustomers();
        return new ResponseEntity<>(tdsCustomerList,HttpStatus.OK);
    }
}
