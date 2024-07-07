package com.isoft.mtax.dto;

import lombok.Data;

@Data
public class OrganizationDTO {

	private Long orgId;

	private String orgName;

	private String email;
	
	private String username;
	
	private String password;

	private String primaryContact;

	private String secondaryContact;

	private String city;

	private String state;

	private String country;

	private String pincode;

	private String address;	
	

}
