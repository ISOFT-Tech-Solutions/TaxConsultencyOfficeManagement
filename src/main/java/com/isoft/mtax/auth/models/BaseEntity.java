package com.isoft.mtax.auth.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class BaseEntity {
	
	@Column(name ="CREATED_DATE",insertable = false,updatable = false)
	private LocalDateTime createdDate;
	
	@Column(name ="CREATED_BY",insertable = false,updatable = false)
	private String createdBy;
	
	@Column(name ="LAST_UPDATED_DATE",insertable = false,updatable = false)
	private LocalDateTime lastUpdateDate;
	
	@Column(name ="UPDATED_BY",insertable = false,updatable = false)
	private String updatedBy;
	
}