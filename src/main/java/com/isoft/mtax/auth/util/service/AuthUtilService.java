package com.isoft.mtax.auth.util.service;

import com.isoft.mtax.dto.EmployeeDTO;
import com.isoft.mtax.dto.OrganizationDTO;

public interface AuthUtilService {
	
	void validateUsername(String username);

	void provideAuthForOrganization(OrganizationDTO org);
	
	void provideAuthForEmployee(EmployeeDTO emp);
}
