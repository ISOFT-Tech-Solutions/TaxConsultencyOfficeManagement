package com.isoft.mtax.dto;

import com.isoft.mtax.entity.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TdsCustomerDto {
    private String customerName;
    private String pan;
    private String email;
    private String mobile;
    private String phoneNo;
    private String tanNumber;
    private boolean active =true;
    private AddressDto addressDto;
}
