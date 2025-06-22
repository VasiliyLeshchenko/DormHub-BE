package org.dormhub.www.controller.rest.dto.rs.equipment;

import org.dormhub.www.controller.rest.dto.rs.apartment.ApartmentBriefResponse;

import java.util.UUID;

public record EquipmentLocationResponse(
        UUID id,
        EquipmentBriefResponse equipment,
        ApartmentBriefResponse apartment,
        Integer quantity
) { }
