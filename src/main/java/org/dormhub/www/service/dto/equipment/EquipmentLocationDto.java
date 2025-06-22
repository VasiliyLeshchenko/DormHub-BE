package org.dormhub.www.service.dto.equipment;

import org.dormhub.www.service.dto.apartment.ApartmentBriefDto;

import java.util.UUID;

public record EquipmentLocationDto(
        UUID id,
        EquipmentBriefDto equipment,
        ApartmentBriefDto apartment,
        Integer quantity
) { }
