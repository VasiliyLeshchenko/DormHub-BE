package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rq.EquipmentRequest;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rs.equipment.EquipmentBriefResponse;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.controller.rest.dto.rs.equipment.EquipmentInfoResponse;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.equipment.EquipmentBriefDto;
import org.dormhub.www.service.dto.equipment.EquipmentInfoDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EquipmentWebMapper extends CommonWebMapper {

    EquipmentBriefDto toDto(EquipmentRequest request);

    List<EquipmentBriefResponse> toDto(List<EquipmentBriefDto> equipments);

    IdResponse toResponse(UUID id);

    PaginationResponse<EquipmentInfoResponse> toResponse(PaginationDto<EquipmentInfoDto> paginationDto);

    EquipmentBriefResponse toResponse(EquipmentBriefDto dto);
}
