package com.isoft.mtax.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.isoft.mtax.dto.Auditable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.core.SpringVersion;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type" , discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "customer_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TDSCustomer.class, name = "TDS"),
        @JsonSubTypes.Type(value = GSTCustomer.class, name = "GST")
})
@Entity
@SuperBuilder
public  class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="customer_name")
    private String customerName;
    @Column(name = "pan")
    private String pan;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "phone_no")
    private String phoneNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;
    private boolean active=true;



}
