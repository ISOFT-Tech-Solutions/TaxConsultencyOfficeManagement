package com.isoft.mtax.controller;

import com.isoft.mtax.dto.TdsFilingDto;
import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.GstFiling;
import com.isoft.mtax.entity.TdsFiling;
import com.isoft.mtax.service.CustomerService;
import com.isoft.mtax.service.FilingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/filings")
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
    @Operation(summary = "Creation of TDS Filing", description = "Creation of Filing After Every Filing for TDS Customer")
    @PostMapping("/tds")
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
     * Get All Tds Filed data
     * @param pageable
     * @return
     */
    @GetMapping("/tds")
    public ResponseEntity<List<TdsFilingDto>> tdsFilings(Pageable pageable,@RequestParam Long orgId){
        List<TdsFilingDto> tdsFilings=  filingService.allTdsFilings(pageable,orgId);
        if(tdsFilings.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tdsFilings);
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
        Page<TdsFilingDto> filings = filingService.findFilingsBasedonCustomer(customerId,pageable);
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
    public ResponseEntity<List<TdsFilingDto>> dueFilings(@RequestParam String dueDate){
         List<TdsFilingDto> dueFilings=filingService.dueFilingsData(dueDate);
         if(dueFilings.isEmpty()){
             return ResponseEntity.noContent().build();
         }
         return ResponseEntity.ok(dueFilings);
     }
     @PostMapping("/gst")
    public ResponseEntity<?> createGstFilings(@RequestParam Long customerId, @RequestBody GstFiling gstFiling){
         Customer customer=customerService.customersDetails(customerId);
         if(customer == null){
             return ResponseEntity.badRequest().body("Customer didn't found");
         }
          GstFiling createdGstFiling=filingService.createGstFilings(gstFiling);
          if(createdGstFiling == null){
              return ResponseEntity.badRequest().body("Gst File not created");

          }
         URI filing = ServletUriComponentsBuilder.fromCurrentRequest()
                 .path("/{id}")
                 .buildAndExpand(createdGstFiling.getId())
                 .toUri();
          return ResponseEntity.created(filing).body(createdGstFiling);
     }
}
