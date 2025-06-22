package org.dormhub.www.storage.mapper;

import org.dormhub.www.service.dto.duty.DutyDto;
import org.dormhub.www.storage.entity.DutyEntity;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DutyStorageMapper {
    
    DutyEntity toEntity(DutyDto dto);

    DutyDto toDto(DutyEntity entity);

    List<DutyDto> toDto(List<DutyEntity> entities);

    List<DutyEntity> toEntity(List<DutyDto> dtos);

}
