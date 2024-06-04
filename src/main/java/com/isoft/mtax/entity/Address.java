package com.isoft.mtax.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @OneToOne(mappedBy = "address" ,cascade = CascadeType.ALL )
    @JsonIgnore
    private TDSCustomer tdsCustomer;

}
