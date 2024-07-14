package com.isoft.mtax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isoft.mtax.dto.EmployeeDTO;
import com.isoft.mtax.dto.OrganizationDTO;
import com.isoft.mtax.service.EmployeeService;

@RestController
@RequestMapping("/api/mtax")
public class EmployeeContoller {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/employees")
    public ResponseEntity<?> addemployee(@RequestBody EmployeeDTO empDTO){
    	EmployeeDTO savedData = employeeService.addEmployee(empDTO);
        return new ResponseEntity<>(savedData,HttpStatus.CREATED);
    }
    @GetMapping("/employees")
    public ResponseEntity<?> allemployees(){
        List<EmployeeDTO> employees=employeeService.allEmployees();
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }
}
