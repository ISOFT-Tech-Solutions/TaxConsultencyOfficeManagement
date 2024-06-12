package com.isoft.mtax.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GST_CUSTOMER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GSTCustomer extends Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gstinNumber;
    private String legalName;
    private String taxPayerType;
    private Date dateOfRegistration;
    private boolean active =true;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

}
