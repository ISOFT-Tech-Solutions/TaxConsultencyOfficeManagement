package com.isoft.mtax.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GstCustomerDto  {
    private String customerName;
    private String pan;
    private String email;
    private String mobile;
    private String phoneNo;
    private boolean active =true;

    private String gstinNumber;
    private String legalName;
    private String taxPayerType;
    private Date dateOfRegistration;
    private AddressDto addressDto;


}
