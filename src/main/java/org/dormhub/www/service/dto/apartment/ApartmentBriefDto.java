package org.dormhub.www.service.dto.apartment;

import lombok.Builder;
import org.dormhub.www.storage.entity.enums.ApartmentType;

import java.util.UUID;

@Builder
public record ApartmentBriefDto(
       UUID id,
       Integer number,
       Character suffix,
       ApartmentType type,
       Integer floor
) { }
