package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rq.DutyRequest;
import org.dormhub.www.controller.rest.dto.rs.duty.DutyResponse;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.service.dto.duty.DutyDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses={TenantWebMapper.class})
public interface DutyWebMapper extends CommonWebMapper {

    @Mapping(source = "apartmentId", target = "apartment.id")
    @Mapping(source = "tenantIds", target = "tenants")
    DutyDto toDto(DutyRequest request);

    List<DutyResponse> toResponse(List<DutyDto> dtos);

    DutyResponse toResponse(DutyDto dto);

    PaginationResponse<DutyResponse> toResponse(PaginationDto<DutyDto> page);

}
