package com.isoft.mtax.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "isoft_Emp")
public class Employee extends BaseEntity {

	@Id
	@Column(name = "EMP_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;

	@NotBlank
	@Size(max = 100)
	@Column(name = "EMP_REC_ID")
	private String empRecId;

	@Size(max = 50)
	@Column(name = "EMP_FIRST_NAME")
	private String empFirstName;

	@Size(max = 50)
	@Column(name = "EMP_LAST_NAME")
	private String empLastName;

	@NotBlank
	@Size(max = 100)
	@Email
	@Column(name = "EMP_EMAIL")
	private String email;

	@Size(max = 50)
	@Column(name = "EMP_City")
	private String empCity;

	@Size(max = 100)
	@Column(name = "EMP_STATE")
	private String empState;

	@Size(max = 100)
	@Column(name = "EMP_COUNTRY")
	private String empCountry;

	@Size(max = 100)
	@Column(name = "EMP_PINCODE")
	private String empPincode;

	@ManyToOne
	@JoinColumn(name = "orgId")
    Organization org;
}
