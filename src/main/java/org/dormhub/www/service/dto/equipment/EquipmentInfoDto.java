package org.dormhub.www.service.dto.equipment;

import java.util.UUID;

public record EquipmentInfoDto(
        UUID id,
        String name,
        Integer inventoryNumber,
        Long total
) { }
