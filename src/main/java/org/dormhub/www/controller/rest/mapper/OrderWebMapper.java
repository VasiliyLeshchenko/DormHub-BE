package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rq.ApartmentRequest;
import org.dormhub.www.controller.rest.dto.rq.OrderRequest;
import org.dormhub.www.controller.rest.dto.rs.OrderResponse;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rs.apartment.ApartmentResponse;
import org.dormhub.www.service.dto.OrderDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.apartment.ApartmentBriefDto;
import org.dormhub.www.service.dto.apartment.ApartmentDto;
import org.dormhub.www.service.dto.staffer.StafferBriefDto;
import org.dormhub.www.service.dto.tenant.TenantBriefDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OrderWebMapper extends CommonWebMapper {

    @Mapping(source = "creatorId", target = "creator", qualifiedByName = "toCreator")
    @Mapping(source = "assignedId", target = "assigned", qualifiedByName = "toAssigned")
    OrderDto toDto(OrderRequest request);

    List<OrderResponse> toResponse(List<OrderDto> dtos);

    OrderResponse toResponse(OrderDto dto);

    PaginationResponse<OrderResponse> toResponse(PaginationDto<OrderDto> page);

    @Named("toAssigned")
    default StafferBriefDto toAssigned(UUID id) {
        if (id == null) {
            return null;
        }

        return StafferBriefDto.builder()
                .id(id)
                .build();
    }

    @Named("toCreator")
    default TenantBriefDto toCreator(UUID id) {
        if (id == null) {
            return null;
        }

        return TenantBriefDto.builder()
                .id(id)
                .build();
    }

}
