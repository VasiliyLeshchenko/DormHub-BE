package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rs.RoleResponse;
import org.dormhub.www.service.dto.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RoleWebMapper extends CommonWebMapper {

    RoleDto toDto(UUID id);

    List<RoleDto> toDto(List<UUID> ids);

    RoleResponse toResponse(RoleDto dto);

    List<RoleResponse> toResponse(List<RoleDto> roles);

}
