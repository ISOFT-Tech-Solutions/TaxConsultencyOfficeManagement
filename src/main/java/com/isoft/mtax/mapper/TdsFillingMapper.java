package com.isoft.mtax.mapper;

import com.isoft.mtax.dto.CustomerDto;
import com.isoft.mtax.dto.EmployeeDTO;
import com.isoft.mtax.dto.TdsFilingDto;
import com.isoft.mtax.entity.Customer;
import com.isoft.mtax.entity.Employee;
import com.isoft.mtax.entity.TdsFiling;
import org.hibernate.annotations.Source;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TdsFillingMapper {
    TdsFillingMapper INSTANCE= Mappers.getMapper(TdsFillingMapper.class);

    TdsFilingDto toTdsFillingDto(TdsFiling tdsFiling);
    List<TdsFilingDto> toDtoList(List<TdsFiling> filings);

}
