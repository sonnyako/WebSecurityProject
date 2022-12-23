package com.example.websecurity.mapper;

import com.example.websecurity.dao.model.Project;
import com.example.websecurity.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto toDto(Project entity);

    Project toEntity(ProjectDto dto);
}
