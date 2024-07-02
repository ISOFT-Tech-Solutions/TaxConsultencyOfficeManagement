package com.isoft.mtax.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class AddressDto {
    private String city;
    private String street;
    private String pinCode;
    private String state;
    private String country;

}
