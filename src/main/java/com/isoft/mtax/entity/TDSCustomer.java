package com.isoft.mtax.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuperBuilder
@Table(name = "tds_customer")
public class TDSCustomer extends Customer{

    @NotBlank(message = "TAN Number is mandatory")
    @Size(min = 10, max = 10, message = "TAN Number must be eight character")
    private String tanNumber;

    private boolean active ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;


}
