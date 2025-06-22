package org.dormhub.www.storage.mapper;

import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.equipment.EquipmentInfoDto;
import org.dormhub.www.service.dto.equipment.EquipmentLocationDto;
import org.dormhub.www.storage.entity.EquipmentLocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EquipmentLocationStorageMapper {

    EquipmentLocationDto toDto(EquipmentLocationEntity entity);

    List<EquipmentLocationDto> toDto(List<EquipmentLocationEntity> equipments);

    EquipmentLocationEntity toEntity(EquipmentLocationDto dto);

    default PaginationDto<EquipmentLocationDto> toDto(Page<EquipmentLocationEntity> page) {
        return new PaginationDto<>(
                toDto(page.getContent()),
                page.getNumberOfElements(),
                page.getNumber(),
                page.getTotalElements()
        );
    }

    @Mapping(source = "equipment.id", target = "id")
    @Mapping(source = "equipment.name", target = "name")
    @Mapping(source = "equipment.inventoryNumber", target = "inventoryNumber")
    @Mapping(source = "quantity", target = "total")
    EquipmentInfoDto toEquipmentInfoDto(EquipmentLocationEntity equipmentLocationEntities);

    List<EquipmentInfoDto> toEquipmentInfoDto(List<EquipmentLocationEntity> equipmentLocationEntities);

    default PaginationDto<EquipmentInfoDto> toPaginationInfoDto(Page<EquipmentLocationEntity> page) {
        return new PaginationDto<>(
                toEquipmentInfoDto(page.getContent()),
                page.getNumberOfElements(),
                page.getNumber(),
                page.getTotalElements()
        );
    }
}
