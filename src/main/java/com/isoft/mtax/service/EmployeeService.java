package com.isoft.mtax.service;

import java.util.List;

import com.isoft.mtax.dto.EmployeeDTO;

public interface EmployeeService {

	List<EmployeeDTO> allEmployees();

	EmployeeDTO addEmployee(EmployeeDTO org);
}
