package com.isoft.mtax.controller;

import com.isoft.mtax.entity.GstFiling;
import com.isoft.mtax.entity.TdsFiling;
import com.isoft.mtax.service.RevnueService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/api/v1/revnue/")
@RestController
@Log4j2
public class RevnueController {
    @Autowired
    private RevnueService revnueService;
    @Operation(summary = "Cacluclate Revnue Based on Month.")
    @GetMapping("/monthly")
    public ResponseEntity<?> calculateRevnueBasedOnMonthly(@RequestParam  int year,@RequestParam  int month){

        double monthlyRevnue=revnueService.calculateRevnueBasedOnMonth(year,month);
       log.info("Revnue Controller "+monthlyRevnue);

        return ResponseEntity.ok("Monthly Revnue :"+monthlyRevnue);
    }
    @Operation(summary = "Caclulate Revnue based on Quarter, Valid value 1, 2 ,3,4 for quarter")
    @GetMapping("/quatrly")
    public ResponseEntity<?> caclulateRevnueBasedonQuaterly(@RequestParam int year,
                                                                 @RequestParam int quarter){
        if(quarter <0 || quarter >4){
            return  ResponseEntity.badRequest().body("Quarter Value is not correct");
        }
        double quartlyRevnue=revnueService.calculateRevnueBasedOnQuarter(year,quarter);
        return ResponseEntity.ok("Quartely Revnue : "+quartlyRevnue);

    }
    @GetMapping ("/yearly")
    @Operation(summary = "Caclulate Revnue based on Per Year.")
    public ResponseEntity<?> calculateRevnueYearly(@RequestParam int year){
        if(year < 0 ){
            return  ResponseEntity.badRequest().body("Year is not valid");
        }
        double yearlyRevnue = revnueService.calculateRevnueBasedOnYear(year);
        return  ResponseEntity.ok("Yearly Revnue : "+yearlyRevnue);
    }
}
