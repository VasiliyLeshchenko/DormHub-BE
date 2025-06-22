package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rq.DormRequest;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rs.dorm.DormBriefResponse;
import org.dormhub.www.controller.rest.dto.rs.dorm.DormResponse;
import org.dormhub.www.service.dto.dorm.DormBriefDto;
import org.dormhub.www.service.dto.dorm.DormDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DormWebMapper extends CommonWebMapper {

    DormDto toDto(DormRequest request);

    List<DormResponse> toResponse(List<DormDto> dtos);

    DormResponse toResponse(DormDto dto);


    PaginationResponse<DormResponse> toResponse(PaginationDto<DormDto> page);

    List<DormBriefResponse> toBriefResponse(List<DormBriefDto> dtos);

}
