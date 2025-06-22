package org.dormhub.www.service.dto.equipment;

import java.util.UUID;

public record EquipmentBriefDto(
        UUID id,
        String name,
        Integer inventoryNumber
) { }
