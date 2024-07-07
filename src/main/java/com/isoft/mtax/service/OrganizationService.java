package com.isoft.mtax.service;

import java.util.List;

import com.isoft.mtax.dto.OrganizationDTO;

public interface OrganizationService {

	List<OrganizationDTO> allOrganizations();

	OrganizationDTO addOrganization(OrganizationDTO org);
}
