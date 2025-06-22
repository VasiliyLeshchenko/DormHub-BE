package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rq.StafferRequest;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rs.staffer.StafferInfoResponse;
import org.dormhub.www.controller.rest.dto.rs.staffer.StafferResponse;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.dorm.DormBriefDto;
import org.dormhub.www.service.dto.dorm.DormDto;
import org.dormhub.www.service.dto.staffer.StafferDto;
import org.dormhub.www.service.dto.staffer.StafferInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = RoleWebMapper.class)
public interface StafferWebMapper extends CommonWebMapper {

    @Mapping(source = "dormId", target = "dorm", qualifiedByName = "toDorm")
    @Mapping(source = "roleIds", target = "roles")
    StafferInfoDto toInfoDto(StafferRequest request);
    
    StafferResponse toResponse(StafferDto dto);

    PaginationResponse<StafferResponse> toResponse(PaginationDto<StafferDto> paginationDto);

    StafferInfoResponse toInfoResponse(StafferInfoDto dto);

    @Named("toDorm")
    default DormDto toDorm(UUID id) {
        if (id == null) {
            return null;
        }

        return DormDto.builder()
                .id(id)
                .build();
    }

}
