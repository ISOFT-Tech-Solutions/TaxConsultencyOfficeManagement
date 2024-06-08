package com.isoft.mtax.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TDS_CUSTOMER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TDSCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "TAN Number is mandatory")
    @Size(min = 10, max = 10, message = "TAN Number must be eight character")
    private String tanNumber;
    private String tdsCustomerName;
    private String pan;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Mobile Number is not valid")

    private String mobile;
    private String phoneNo;
    private boolean active =true;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;


}
