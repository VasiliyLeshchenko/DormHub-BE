package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rs.tenant.TenantInfoResponse;
import org.dormhub.www.controller.rest.dto.rs.tenant.TenantResponse;
import org.dormhub.www.controller.rest.dto.rq.TenantRequest;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.TenantInfoDto;
import org.dormhub.www.service.dto.apartment.ApartmentBriefDto;
import org.dormhub.www.service.dto.tenant.TenantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = RoleWebMapper.class)
public interface TenantWebMapper extends CommonWebMapper {

    @Mapping(source = "dormId", target = "dorm.id", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "apartmentId", target = "apartment", qualifiedByName = "toApartment")
    @Mapping(source = "roleIds", target = "roles")
    TenantInfoDto toInfoDto(TenantRequest request);

    TenantResponse toResponse(TenantDto dto);

    PaginationResponse<TenantResponse> toResponse(PaginationDto<TenantDto> paginationDto);

    TenantInfoResponse toInfoResponse(TenantInfoDto dto);

    TenantDto toDto(UUID id);

    @Named("toApartment")
    default ApartmentBriefDto toApartment(UUID id) {
        if (id == null) {
            return null;
        }

        return ApartmentBriefDto.builder()
                .id(id)
                .build();
    }

}
