package com.isoft.mtax.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("GST")
@Entity
@SuperBuilder
public class GSTCustomer extends Customer implements Serializable {


    private String gstinNumber;
    private String legalName;
    private String taxPayerType;
    private Date dateOfRegistration;

  /*  @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;*/

}
