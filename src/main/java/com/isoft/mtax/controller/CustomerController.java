package com.isoft.mtax.controller;

import com.isoft.mtax.dto.AddressDto;
import com.isoft.mtax.dto.GstCustomerDto;
import com.isoft.mtax.dto.TdsCustomerDto;
import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;
import com.isoft.mtax.service.CustomerService;
import com.isoft.mtax.service.UploadService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/customers")
@Validated
@Log4j2
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic}")
    private String kafkaTopic;
    @Autowired
    private UploadService uploadService;

    /**
     * Added  TDS Customer
     * @param tdsCustomer
     * @return Added Customer Detail with Status Created
     */
    @PostMapping("/")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
      Customer savedCustomer =customerService.save(customer);
     kafkaTemplate.send(kafkaTopic,"TDS Customer "+customer.getCustomerName()+" Added Succufully ");

      return new ResponseEntity<>(customer.getCustomerName()+"Added Succussfully and Email Send", HttpStatus.CREATED);
    }

    /**
     * Serch All Tds Customers
     * @param pageable
     * @return
     */
    @GetMapping("/tds")
    public ResponseEntity<?> findAllTdsCustomers(Pageable pageable){
       Page<TDSCustomer> tdsCustomers=customerService.findAllTdsCustomers(pageable);
       if(tdsCustomers.isEmpty()){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.ok(tdsCustomers);

    }
    /**
     * Search all TDS Customer
     * Search Customer based on City if City Request Param will be provided
     * @return TDS Customer List Data
     */
    @GetMapping ("/{city}")
    public ResponseEntity<?> tdsCustomers(@PathVariable(required = false) String city){
        log.info("tds customer "+city);
        List<Customer> customerList=new ArrayList<>();
        if(city!=null){
            List<Map<String, Object>> tdsCustomersByCity =customerService.findTdsCustomerByAddressCity(city);
            return ResponseEntity.ok(tdsCustomersByCity);
        }
        else {
            log.info("In else condition");
            customerList=customerService.tdsCustomers();
        }
        if(customerList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(customerList);
    }

    /**
     * Serach user based on tanNumber
     * @param tanNumber
     * @return TDSCustomer based on search cretria
     *   or an HTTP status code 204 (No Content) if no users are found
     */
    @GetMapping("/tds/{tan-number}")
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
    @PutMapping("/tds/{id}")
    public ResponseEntity<?> updateTdsCustomer(@PathVariable Long id, @RequestBody TDSCustomer updatedTDSCustomer){
        Customer updatedCustomer =customerService.updateTDSCustomer(id,updatedTDSCustomer);
        return new  ResponseEntity<>(updatedCustomer,HttpStatus.OK);


    }
    @DeleteMapping("/tds/{id}")
    public ResponseEntity<?> deactivateTdsCustomer(@PathVariable Long id){
        Customer tdsCustomer=customerService.deactivateTdsCustomer(id);
        return new ResponseEntity<>("TDS Customer : "+tdsCustomer.getCustomerName()+" Deactivated Succussfully",HttpStatus.OK);
    }
    @PutMapping("/tds/restore/{id}")
    public ResponseEntity<?> restoreTdsCustomer(@PathVariable Long id){
        Customer customer=customerService.restoreTdsCustomer(id);
        return new ResponseEntity<>("TDS Customer : "+customer.getCustomerName()+" Restored  Succussfully",HttpStatus.OK);
    }

    /**
     * Added GST Customer
     * @param gstCustomer
     * @return GST
     */
    @PostMapping("/gst")
    public ResponseEntity<?> addGSTCustomer(@RequestBody  GstCustomerDto gstCustomerDto){
       /* GSTCustomer addedGstCustomer =customerService.addGstCustomer(gstCustomer);*//*
        kafkaTemplate.send(kafkaTopic,"GST Customer" +addedGstCustomer.getCustomerName()+" Added Succufully ");
        return new ResponseEntity<>(addedGstCustomer.getCustomerName()+"Added Succussfully and Email Send", HttpStatus.CREATED);*/
        return null;
    }

    /**
     * Get All GST Customer using Pagingnation
     * based on Page and Size
     * @param page
     * @param size
     * @return GST Customer List
     */
    @GetMapping("/gst")
    public ResponseEntity<?> allGstCustomers(
            @RequestParam (defaultValue = "0")int page,
            @RequestParam (defaultValue = "10") int size){
       Page<GSTCustomer> gstCustomers=customerService.gstCustomers(page,size);
       if(gstCustomers.isEmpty()){
           return ResponseEntity.noContent().build();
       }
     return new ResponseEntity<>(gstCustomers,HttpStatus.OK);
    }

    /**
     * Get GST Customer based on GSTIN Number
     * @param gstinNumber
     * @return GST Customer
     */

    @GetMapping("/gst/{gstin-number}")
    public ResponseEntity<?> gstCustomerbasedOnGstinNumber(@PathVariable("gstin-number") String gstinNumber){
        return null;
    }
   @PutMapping("/customers/gst/{id}")
    public ResponseEntity<?> updateGstCustomer(@PathVariable Long id,@RequestBody GSTCustomer customer){

           /* GSTCustomer updatedGstCustomer = customerService.updateGstCustomer(id,customer);
            return new ResponseEntity<>(updatedGstCustomer.getCustomerName() +" : updated Succussfully",HttpStatus.OK);*/
       return null;


        }
        @GetMapping("/gst/{id}")
    public ResponseEntity<?> customerDetail(@PathVariable Long id){
      /*  Optional<GSTCustomer> gstCustomer=customerService.gstCustomerDetails(id);
        return ResponseEntity.ok(gstCustomer.get());*/
            return null;

    }
    @GetMapping("/tds/{id}")
    public ResponseEntity<?> tdsCustomerDetail(@PathVariable Long id){
       Customer customer=customerService.customersDetails(id);
        log.info("Tds Customer name "+customer.getCustomerName());
            return ResponseEntity.ok(customer);
    }
    /**
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/csv-upload")
    public ResponseEntity<?> uploadTdsCustomerUsingCsv(@RequestParam("file") MultipartFile file,@RequestParam String type)  {
        log.info("CSV File upload");
        if("tds".equalsIgnoreCase(type)) {
            TDSCustomer customer = new TDSCustomer();
            List<String> savedGstCustomer = uploadService.processTdsCustomerCSV(file);

            try {
                return ResponseEntity.ok(savedGstCustomer);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File processing failed: " + e.getMessage());

            }
        } else if ("gst".equalsIgnoreCase(type)) {
            log.info("Inside gst customer upload");
            GSTCustomer gstCustomer =new GSTCustomer();
            List<String> savedGstCustomers=uploadService.processGstCustomerCsv(file);
            try {
                return ResponseEntity.ok(savedGstCustomers);
            }catch (RuntimeException re) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File processing failed: " + re.getMessage());
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
