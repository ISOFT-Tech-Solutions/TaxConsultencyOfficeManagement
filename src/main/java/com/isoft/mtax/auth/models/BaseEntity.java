package com.isoft.mtax.auth.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class BaseEntity {
	
	@Column(name ="CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name ="CREATED_BY")
	private String createdBy;
	
	@Column(name ="LAST_UPDATED_DATE")
	private LocalDateTime lastUpdateDate;
	
	@Column(name ="UPDATED_BY")
	private String updatedBy;
	
}