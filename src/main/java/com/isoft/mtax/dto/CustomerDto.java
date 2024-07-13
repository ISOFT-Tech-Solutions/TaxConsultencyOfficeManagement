package com.isoft.mtax.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Builder
@Getter
@Setter
public class CustomerDto {
    private String customerName;
    private String pan;
    private String email;
    private String mobile;
    private String phoneNo;
    private boolean active =true;
    private AddressDto addressDto;

}
