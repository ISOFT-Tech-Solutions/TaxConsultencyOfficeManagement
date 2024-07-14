package com.isoft.mtax.service;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.isoft.mtax.dto.EmployeeDTO;
import com.isoft.mtax.dto.OrganizationDTO;
import com.isoft.mtax.entity.Employee;
import com.isoft.mtax.entity.Organization;

@Mapper
public interface DataMapper {
	
	DataMapper INSTANCE = Mappers.getMapper(DataMapper.class);
	

	Organization mapOrganizationDTOToOrganization(OrganizationDTO organizationDto);

	OrganizationDTO mapOrganizationToOrganizationDTO(Organization organization);
	
	Employee mapEmployeeDTOToEmployee(EmployeeDTO empDTO);
	
	EmployeeDTO mapEmployeeToEmployeeDTO(Employee emp);

}
