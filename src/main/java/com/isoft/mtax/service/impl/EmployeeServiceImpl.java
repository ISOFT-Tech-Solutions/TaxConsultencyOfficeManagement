package com.isoft.mtax.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isoft.mtax.auth.util.service.AuthUtilService;
import com.isoft.mtax.dto.EmployeeDTO;
import com.isoft.mtax.entity.Employee;
import com.isoft.mtax.entity.Organization;
import com.isoft.mtax.exception.ResourceNotFoundException;
import com.isoft.mtax.repo.EmployeeRepo;
import com.isoft.mtax.repo.OrganizationRepo;
import com.isoft.mtax.service.DataMapper;
import com.isoft.mtax.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private OrganizationRepo orgRepo;

	@Autowired
	private AuthUtilService authUtilService;

	@Override
	public List<EmployeeDTO> allEmployees() {
		return employeeRepo.findAll().stream().map(emp -> DataMapper.INSTANCE.mapEmployeeToEmployeeDTO(emp)).toList();
	}

	@Override
	@Transactional
	public EmployeeDTO addEmployee(EmployeeDTO empDto) {
		authUtilService.validateUsername(empDto.getUsername());
		Employee employee = DataMapper.INSTANCE.mapEmployeeDTOToEmployee(empDto);
		Organization org = orgRepo.findById(empDto.getOrgId()).orElseThrow(() -> new ResourceNotFoundException(
				"Provided Organization  id : " + empDto.getOrgId() + " not present in system."));
		employee.setOrg(org);
		Employee saveEmp = employeeRepo.save(employee);
		authUtilService.provideAuthForEmployee(empDto);
		return DataMapper.INSTANCE.mapEmployeeToEmployeeDTO(saveEmp);
	}

}
