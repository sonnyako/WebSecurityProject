package com.example.websecurity.mapper;

import com.example.websecurity.dao.model.RoleName;
import com.example.websecurity.dao.model.User;
import com.example.websecurity.dto.RegistrationDto;
import com.example.websecurity.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

   UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

   @Mapping(source = "id", target = "id")
   @Mapping(source = "username", target = "username")
   UserDto toDto(User entity);

   @Mapping(source = "role", target = "role.id", qualifiedByName = "roleNameToNumber")
   @Mapping(source = "role", target = "role.roleName")
   User toEntity(RegistrationDto dto);

   @Named("numberToRoleName")
   static RoleName convertCoverType(Integer roleNum) {
      return RoleName.values()[roleNum];
   }

   @Named("roleNameToNumber")
   static Integer convertRoleName(RoleName roleName) {
      return roleName.ordinal() + 1;
   }
}
