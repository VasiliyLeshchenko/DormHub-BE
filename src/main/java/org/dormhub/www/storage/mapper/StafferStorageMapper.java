package org.dormhub.www.storage.mapper;

import org.dormhub.www.service.dto.staffer.StafferDto;
import org.dormhub.www.service.dto.staffer.StafferInfoDto;
import org.dormhub.www.storage.entity.StafferEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface StafferStorageMapper {

    StafferEntity toEntity(StafferInfoDto dro);

    StafferDto toDto(StafferEntity dro);

    StafferInfoDto toInfoDto(StafferEntity dro);

    @AfterMapping
    default void setUserBackReference(@MappingTarget StafferEntity entity) {
        if (entity.getCredential() != null) {
            entity.getCredential().setUser(entity);
        }
    }

    default List<StafferDto> toListDto(Page<StafferEntity> staffers) {
        return staffers.getContent()
                .stream()
                .map(this::toDto)
                .toList();
    }
}
