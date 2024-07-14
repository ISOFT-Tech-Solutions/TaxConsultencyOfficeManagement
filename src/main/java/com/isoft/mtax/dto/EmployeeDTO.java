package com.isoft.mtax.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

	private Long empId;

	private String empRecId;

	private String empFirstName;

	private String empLastName;

	private String username;

	private String password;

	private String email;

	private String empCity;

	private String empState;

	private String empCountry;

	private String empPincode;

	private String empAddress;

	private Long orgId;

	private OrganizationDTO org;

}
