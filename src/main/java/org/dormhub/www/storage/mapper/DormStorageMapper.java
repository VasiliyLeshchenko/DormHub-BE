package org.dormhub.www.storage.mapper;

import org.dormhub.www.service.dto.dorm.DormBriefDto;
import org.dormhub.www.service.dto.dorm.DormDto;
import org.dormhub.www.storage.entity.DormEntity;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface DormStorageMapper {

    DormEntity toEntity(DormDto dto);

    DormDto toDto(DormEntity entity);

    List<DormDto> toDto(List<DormEntity> entities);

    List<DormBriefDto> toBriefDto(List<DormEntity> dorms);

}
