package com.example.eduwise.mapper;

import com.example.eduwise.model.dto.RoleDto;
import com.example.eduwise.model.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleDto roleDto);

    RoleDto toRoleDto(Role role);

    List<RoleDto> toRoleDtoList(List<Role> roles);
}
