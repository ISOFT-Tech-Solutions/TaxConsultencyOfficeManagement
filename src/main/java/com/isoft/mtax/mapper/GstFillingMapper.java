package com.isoft.mtax.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GstFillingMapper {
    GstFillingMapper INSTANCE= Mappers.getMapper(GstFillingMapper.class);

}
