package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EquipmentRequest(
        UUID id,

        @NotBlank(message = "Не указано наименование оборудования")
        String name,

        @NotNull(message = "Отсутствует инвентарный номер")
        Integer inventoryNumber
) { }
