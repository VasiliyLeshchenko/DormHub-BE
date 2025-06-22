package org.dormhub.www.storage.mapper;

import org.dormhub.www.service.dto.OrderDto;
import org.dormhub.www.storage.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OrderStorageMapper {
    
    OrderEntity toEntity(OrderDto dto);

    OrderDto toDto(OrderEntity entity);

    List<OrderDto> toDto(List<OrderEntity> entities);

    List<OrderEntity> toEntity(List<OrderDto> dtos);

}
