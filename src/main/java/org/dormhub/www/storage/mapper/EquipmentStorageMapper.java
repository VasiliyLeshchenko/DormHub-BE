package org.dormhub.www.storage.mapper;

import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.equipment.EquipmentBriefDto;
import org.dormhub.www.service.dto.equipment.EquipmentInfoDto;
import org.dormhub.www.service.dto.equipment.EquipmentLocationDto;
import org.dormhub.www.storage.entity.EquipmentEntity;
import org.dormhub.www.storage.entity.EquipmentLocationEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EquipmentStorageMapper {

    EquipmentBriefDto toDto(EquipmentEntity entity);

    EquipmentInfoDto toInfoDto(EquipmentEntity entity);

    EquipmentLocationDto toDto(EquipmentLocationEntity entity);

    List<EquipmentBriefDto> toDto(List<EquipmentEntity> equipments);

    List<EquipmentInfoDto> toInfoDto(List<EquipmentEntity> equipments);

    EquipmentEntity toEntity(EquipmentBriefDto dto);

    default PaginationDto<EquipmentInfoDto> toDto(Page<EquipmentInfoDto> page) {
        return new PaginationDto<>(
                page.getContent(),
                page.getNumberOfElements(),
                page.getNumber(),
                page.getTotalElements()
        );
    }

    default PaginationDto<EquipmentInfoDto> toInfoDto(Page<EquipmentEntity> page) {
        return new PaginationDto<>(
                toInfoDto(page.getContent()),
                page.getNumberOfElements(),
                page.getNumber(),
                page.getTotalElements()
        );
    }

}
