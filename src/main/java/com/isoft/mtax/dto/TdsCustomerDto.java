package com.isoft.mtax.dto;

import com.isoft.mtax.entity.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TdsCustomerDto  {
    private String customerName;
    private String pan;
    private String email;
    private String mobile;
    private String phoneNo;
    private boolean active =true;
    private AddressDto addressDto;


    private String tanNumber;


}

