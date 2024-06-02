package com.isoft.mtax.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TDS_CUSTOMER")
public class TDSCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tanNumber;
    private String tdsCustomerName;
    private String email;
    private String mobile;
    private String phoneNo;
    @OneToOne
    private Address address;
}
