package org.dormhub.www.storage.mapper;

import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.apartment.ApartmentDto;
import org.dormhub.www.storage.entity.ApartmentEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ApartmentStorageMapper {

    @Mapping(source = "children", target = "children", ignore = true)
    ApartmentEntity toEntity(ApartmentDto dto);

    ApartmentDto toDto(ApartmentEntity entity);

    List<ApartmentDto> toDto(List<ApartmentEntity> entities);

    List<ApartmentEntity> toEntity(List<ApartmentDto> dtos);

    @AfterMapping
    default void handleChildren(@MappingTarget ApartmentEntity entity, ApartmentDto dto) {
        if (dto.children() != null && !dto.children().isEmpty()) {
            List<ApartmentEntity> children = dto.children().stream()
                    .map(childDto -> {
                        ApartmentEntity child = toEntity(childDto);
                        child.setParent(entity);
                        return child;
                    })
                    .toList();

            entity.setChildren(children);
        }
    }

    default PaginationDto<ApartmentDto> toDto(Page<ApartmentEntity> entities) {
        return new PaginationDto<>(
            toDto(entities.getContent()),
            entities.getNumberOfElements(),
            entities.getNumber(),
            entities.getTotalElements()
        );
    }

    default PaginationDto<ApartmentDto> toPaginationDto(
            List<ApartmentEntity> apartments,
            int numberOfElements, int number, long totalElements) {
        return new PaginationDto<>(
                toDto(apartments),
                numberOfElements,
                number,
                totalElements
        );
    }
}
