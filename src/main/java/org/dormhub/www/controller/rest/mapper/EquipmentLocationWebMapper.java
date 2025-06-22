package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rq.EquipmentLocationRequest;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rs.equipment.EquipmentLocationResponse;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.equipment.EquipmentLocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EquipmentLocationWebMapper extends CommonWebMapper {

    @Mapping(source = "equipmentId", target = "equipment.id")
    @Mapping(source = "apartmentId", target = "apartment.id")
    EquipmentLocationDto toDto(EquipmentLocationRequest request);

    List<EquipmentLocationResponse> toDto(List<EquipmentLocationDto> equipments);

    IdResponse toResponse(UUID id);

    PaginationResponse<EquipmentLocationResponse> toResponse(PaginationDto<EquipmentLocationDto> paginationDto);

    EquipmentLocationResponse toResponse(EquipmentLocationDto dto);
}
