package com.isoft.mtax.auth.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "isoft_Org")
public class Morganization extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

}