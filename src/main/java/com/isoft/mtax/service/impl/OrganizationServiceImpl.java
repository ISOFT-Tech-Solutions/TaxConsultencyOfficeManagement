package com.isoft.mtax.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isoft.mtax.auth.util.service.AuthUtilService;
import com.isoft.mtax.dto.OrganizationDTO;
import com.isoft.mtax.entity.Organization;
import com.isoft.mtax.repo.OrganizationRepo;
import com.isoft.mtax.service.DataMapper;
import com.isoft.mtax.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationRepo organizationRepo;

	@Autowired
	private AuthUtilService authUtilService;

	@Override
	public List<OrganizationDTO> allOrganizations() {
		return organizationRepo.findAll().stream().map(org -> DataMapper.INSTANCE.mapOrganizationToOrganizationDTO(org))
				.collect(Collectors.toList());

	}

	@Override
	public OrganizationDTO addOrganization(OrganizationDTO org) {
		authUtilService.validateUsername(org.getUsername());
		Organization savedOrg = organizationRepo.save(DataMapper.INSTANCE.mapOrganizationDTOToOrganization(org));
		authUtilService.provideAuthForOrganization(org);
		return DataMapper.INSTANCE.mapOrganizationToOrganizationDTO(savedOrg);
	}

}
