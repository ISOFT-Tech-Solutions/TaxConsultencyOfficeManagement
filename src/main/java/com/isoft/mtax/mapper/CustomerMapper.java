package com.isoft.mtax.mapper;

import com.isoft.mtax.constants.CsvConstants;
import com.isoft.mtax.dto.AddressDto;
import com.isoft.mtax.dto.CustomerDto;
import com.isoft.mtax.dto.GstCustomerDto;
import com.isoft.mtax.dto.TdsCustomerDto;
import com.isoft.mtax.entity.Address;
import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.GSTCustomer;
import com.isoft.mtax.entity.TDSCustomer;

public class CustomerMapper {
    public static Address toEntity(AddressDto dto) {
        return Address.builder()
                .street(dto.getStreet())
                .city(dto.getCity())
                .state(dto.getState())
                .pinCode(dto.getPinCode())
                .build();
    }
    public static Customer toEntity(CustomerDto dto) {
       return   Customer.builder()
                .customerName(dto.getCustomerName())
                .pan(dto.getPan())
                .email(dto.getEmail())
                .phoneNo(dto.getPhoneNo())
                .mobile(dto.getMobile())
                .active(dto.isActive())
               .address(toEntity(dto.getAddressDto()))
               .build();
    }
    public static TDSCustomer toEntity(TdsCustomerDto tdsCustomerDto){
       return TDSCustomer.builder()
                .customerName(tdsCustomerDto.getCustomerName())
                .pan(tdsCustomerDto.getPan())
                .email(tdsCustomerDto.getEmail())
                .phoneNo(tdsCustomerDto.getPhoneNo())
                .mobile(tdsCustomerDto.getMobile())
                .active(tdsCustomerDto.isActive())
                .tanNumber(tdsCustomerDto.getTanNumber())
                .address(toEntity(tdsCustomerDto.getAddressDto()))
                .build();
    }
    public static GSTCustomer toEntity(GstCustomerDto  gstCustomerDto){
        return GSTCustomer.builder()
                .customerName(gstCustomerDto.getCustomerName())
                .pan(gstCustomerDto.getPan())
                .email(gstCustomerDto.getEmail())
                .phoneNo(gstCustomerDto.getPhoneNo())
                .mobile(gstCustomerDto.getMobile())
                .active(gstCustomerDto.isActive())
                .gstinNumber(gstCustomerDto.getGstinNumber())
                .legalName(gstCustomerDto.getLegalName())
                .dateOfRegistration(gstCustomerDto.getDateOfRegistration())
                .build();
    }



}

