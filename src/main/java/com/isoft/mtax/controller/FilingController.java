package com.isoft.mtax.controller;

import com.isoft.mtax.dto.FilingDto;
import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.TdsFiling;
import com.isoft.mtax.service.CustomerService;
import com.isoft.mtax.service.FilingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mtax/filing")
@Log4j2
public class FilingController {
    @Autowired
    private FilingService filingService;
    @Autowired
    private CustomerService customerService;

    /**
     * Create Filing Request for Customer
     * @param customerId
     * @param filingRequest
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<String> createFilingsForCustomer(@RequestParam Long customerId, @RequestBody TdsFiling filingRequest){
        log.info("Create Filing Customer");
        Customer customer=customerService.customersDetails(customerId);
        filingRequest.setTdsCustomer(customer);
        log.info("Tds Customer Id "+customer.getId());
        if(customer == null){
            return  ResponseEntity.badRequest().body("Customer Not Found");
        }
         String message =filingService.createFilingsForCustomer(customer,filingRequest);
         return ResponseEntity.ok("Filing Created Succfully");

    }

    /**
     * Find Filing for customer based
     * @param customerId
     * @return
     * Filing List
     */
     @GetMapping("/customer/{customerId}")
     public ResponseEntity<?> findFilingsBasedonCustomer(@PathVariable Long customerId, Pageable pageable){
        Customer customer=customerService.customersDetails(customerId);
        if(customer==null){
            return ResponseEntity.badRequest().body("Customer not found");
        }
        Page<FilingDto> filings = filingService.findFilingsBasedonCustomer(customerId,pageable);
        if(filings.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(filings);
     }
     @PutMapping("/{filingId}/mark-complete")
    public ResponseEntity<?> filingMarkComplete(@PathVariable Long filingId){
         TdsFiling filing=filingService.findFilingById(filingId);
         if(filing == null){
             return ResponseEntity.noContent().build();
         }
         filing.setFilingStatus("Filed");

         TdsFiling competedFiling =filingService.filingMarkComplete(filing);
         return ResponseEntity.ok(competedFiling);

     }
     @GetMapping("/due")
    public ResponseEntity<List<FilingDto>> dueFilings(@RequestParam String dueDate){
         List<FilingDto> dueFilings=filingService.dueFilingsData(dueDate);
         if(dueFilings.isEmpty()){
             return ResponseEntity.noContent().build();
         }
         return ResponseEntity.ok(dueFilings);
     }
}
