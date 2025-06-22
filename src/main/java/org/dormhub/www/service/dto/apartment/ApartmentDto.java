package org.dormhub.www.service.dto.apartment;

import lombok.Builder;
import org.dormhub.www.service.dto.dorm.DormBriefDto;
import org.dormhub.www.storage.entity.enums.ApartmentType;

import java.util.List;
import java.util.UUID;

@Builder
public record ApartmentDto(
       UUID id,
       Integer number,
       Character suffix,
       ApartmentType type,
       Integer size,

       Integer floor,
       DormBriefDto dorm,
       ApartmentBriefDto parent,
       List<ApartmentDto> children
) { }
