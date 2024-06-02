package com.isoft.mtax.service;

import com.isoft.mtax.entity.Organization;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface OrganizationService {
    List<Organization> allOrganizations();
}
