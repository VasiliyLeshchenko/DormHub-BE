package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rq.ApartmentRequest;
import org.dormhub.www.controller.rest.dto.rs.apartment.ApartmentResponse;
import org.dormhub.www.service.dto.apartment.ApartmentBriefDto;
import org.dormhub.www.service.dto.apartment.ApartmentDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ApartmentWebMapper extends CommonWebMapper {

    @Mapping(source = "dormId", target = "dorm.id")
    @Mapping(source = "parentId", target = "parent", qualifiedByName = "mapParentIdToParent")
    ApartmentDto toDto(ApartmentRequest request);

    List<ApartmentResponse> toResponse(List<ApartmentDto> dtos);

    ApartmentResponse toResponse(ApartmentDto dto);

    PaginationResponse<ApartmentResponse> toResponse(PaginationDto<ApartmentDto> page);

    @Named("mapParentIdToParent")
    default ApartmentBriefDto mapParentIdToParent(UUID parentId) {
        if (parentId == null) {
            return null;
        }

        return ApartmentBriefDto.builder()
                .id(parentId)
                .build();
    }

}
