package com.isoft.mtax.auth.util.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isoft.mtax.auth.models.ERole;
import com.isoft.mtax.auth.models.Role;
import com.isoft.mtax.auth.models.User;
import com.isoft.mtax.auth.repository.RoleRepository;
import com.isoft.mtax.auth.repository.UserRepository;
import com.isoft.mtax.dto.EmployeeDTO;
import com.isoft.mtax.dto.OrganizationDTO;
import com.isoft.mtax.exception.DuplicateResourceFoundException;

@Service
public class AuthUtilServiceImpl implements AuthUtilService {
	@Autowired
	UserRepository userRepository;
	
	  @Autowired
	  RoleRepository roleRepository;

	  @Autowired
	  PasswordEncoder encoder;


	@Override
	public void provideAuthForOrganization(OrganizationDTO org) {

		 // Create new user's account
	    User user = new User(org.getUsername(), org.getEmail(),
	        encoder.encode(org.getPassword()));
	    Set<Role> roles = new HashSet<>();
	    Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
		          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		      roles.add(userRole);
	    
	    user.setRoles(roles);
	    userRepository.save(user);
	}

	@Override
	public void validateUsername(String username) {

		if (userRepository.existsByUsername(username)) {
			throw new DuplicateResourceFoundException("Username already exit.");

		}
	}

	@Override
	public void provideAuthForEmployee(EmployeeDTO emp) {
		 // Create new user's account
	    User user = new User(emp.getUsername(), emp.getEmail(),
	        encoder.encode(emp.getPassword()));
	    Set<Role> roles = new HashSet<>();
	    Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
		          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		      roles.add(userRole);
	    
	    user.setRoles(roles);
	    userRepository.save(user);
		
	}
}
