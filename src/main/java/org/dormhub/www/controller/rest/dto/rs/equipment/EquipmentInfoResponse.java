package org.dormhub.www.controller.rest.dto.rs.equipment;

import java.util.UUID;

public record EquipmentInfoResponse(
        UUID id,
        String name,
        Integer inventoryNumber,
        Long total
) { }
