package org.dormhub.www.storage.mapper;

import org.dormhub.www.service.dto.TenantInfoDto;
import org.dormhub.www.service.dto.tenant.TenantDto;
import org.dormhub.www.storage.entity.TenantEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface TenantStorageMapper {

    TenantEntity toEntity(TenantInfoDto dto);

    TenantDto toDto(TenantEntity dto);

    TenantInfoDto toInfoDto(TenantEntity dto);

    @AfterMapping
    default void setUserBackReference(@MappingTarget TenantEntity entity) {
        if (entity.getCredential() != null) {
            entity.getCredential().setUser(entity);
        }
    }

    default List<TenantDto> toListDto(Page<TenantEntity> tenants) {
        return tenants.getContent()
                .stream()
                .map(this::toDto)
                .toList();
    }

}
