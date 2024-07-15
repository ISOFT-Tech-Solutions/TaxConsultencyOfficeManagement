package com.isoft.mtax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isoft.mtax.dto.OrganizationDTO;
import com.isoft.mtax.service.OrganizationService;

@RestController
@RequestMapping("/api/v1")
public class OrganizationContoller {
    @Autowired
    OrganizationService organizationService;
    @PostMapping("/organizations")
    public ResponseEntity<?> addOrganization(@RequestBody OrganizationDTO organization){
    	OrganizationDTO savedData = organizationService.addOrganization(organization);
        return new ResponseEntity<>(savedData,HttpStatusCode.valueOf(200));
    }
    @GetMapping("/organizations")
    public ResponseEntity<?> allOrgnizations(){
        List<OrganizationDTO> organizations=organizationService.allOrganizations();
        return new ResponseEntity<>(organizations,HttpStatusCode.valueOf(200));
    }
}
