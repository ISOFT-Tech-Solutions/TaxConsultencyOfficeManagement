package com.isoft.mtax.dto;

import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.Employee;
import com.isoft.mtax.entity.TDSCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TdsFilingDto {
    private Long id;
    private boolean filed;
    private LocalDate filingDate;
    private LocalDate dueDate;
    private Long tdsFileCharge;
    private EmployeeDTO employee;
    private TdsCustomerDto tdsCustomer;
    private OrganizationDTO organizationDTO;




}
