package com.example.websecurity.mapper;

import com.example.websecurity.dao.model.Cv;
import com.example.websecurity.dto.CvDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class})
public interface CvMapper {

    CvMapper INSTANCE = Mappers.getMapper(CvMapper.class);

    CvDto toDto(Cv entity);

    Cv toEntity(CvDto dto);
}
