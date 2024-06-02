package com.isoft.mtax.controller;

import com.isoft.mtax.entity.Organization;
import com.isoft.mtax.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/test/mtax")
public class OrganizationContoller {
    @Autowired
    OrganizationService organizationService;
    @PostMapping("/organizations")
    public ResponseEntity<?> addOrganization(@RequestBody Organization organization){
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
    @GetMapping("/organizations")
    public ResponseEntity<?> allOrgnizations(){
        List<Organization> organizations=organizationService.allOrganizations();
        return new ResponseEntity<>(organizations,HttpStatusCode.valueOf(200));
    }
}
