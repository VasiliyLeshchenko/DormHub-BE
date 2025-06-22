package org.dormhub.www.storage.mapper;

import org.dormhub.www.service.dto.RoleDto;
import org.dormhub.www.storage.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RoleStorageMapper {

    RoleEntity toEntity(RoleDto dto);

    RoleDto toDto(RoleEntity entity);

    List<RoleDto> toDto(List<RoleEntity> entities);

}
