package com.isoft.mtax.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "isoft_Org")
public class Morganization extends BaseEntity {
	@Id
	@Column(name = "ORG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orgId;

	@NotBlank
	@Size(max = 100)
	@Column(name = "ORG_NAME")
	private String orgName;

	@NotBlank
	@Size(max = 100)
	@Email
	@Column(name = "ORG_EMAIL")
	private String email;

	@NotBlank
	@Size(max = 20)
	@Column(name = "ORG_CONTACT_NUMBER1")
	private String primaryContact;

	@Size(max = 20)
	@Column(name = "ORG_CONTACT_NUMBER2")
	private String secondaryContact;

	@NotBlank
	@Size(max = 100)
	@Column(name = "ORG_City")
	private String city;

	@NotBlank
	@Size(max = 100)
	@Column(name = "ORG_STATE")
	private String state;

	@NotBlank
	@Size(max = 100)
	@Column(name = "ORG_COUNTRY")
	private String country;

	@NotBlank
	@Size(max = 100)
	@Column(name = "ORG_PINCODE")
	private String pincode;

	@NotBlank
	@Size(max = 100)
	@Column(name = "ORG_ADDRESS")
	private String address;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Employee> underEmployees;

}