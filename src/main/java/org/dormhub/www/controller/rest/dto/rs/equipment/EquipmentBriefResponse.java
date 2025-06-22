package org.dormhub.www.controller.rest.dto.rs.equipment;

import java.util.UUID;

public record EquipmentBriefResponse(
        UUID id,
        String name,
        Integer inventoryNumber
) { }
