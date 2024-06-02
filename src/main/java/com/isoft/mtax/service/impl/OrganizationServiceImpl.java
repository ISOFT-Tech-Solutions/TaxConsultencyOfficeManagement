package com.isoft.mtax.service.impl;

import com.isoft.mtax.entity.Organization;
import com.isoft.mtax.repo.OrganizationRepo;
import com.isoft.mtax.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepo organizationRepo;
    @Override
    public List<Organization> allOrganizations() {
        return  organizationRepo.findAll();
    }
}
