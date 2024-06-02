package com.isoft.mtax.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "GST_CUSTOMER")
public class GSTCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String legalName;
    private String businessName;
    private String panNo;
    private String email;
    private String mobile;
    private String phoneNo;

}
