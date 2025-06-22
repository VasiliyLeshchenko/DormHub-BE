package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EquipmentLocationRequest(
        UUID id,

        @NotNull(message = "Не указано оборудование")
        UUID equipmentId,

        @NotNull(message = "Не указано помещение")
        UUID apartmentId,

        @NotNull(message = "Не указано количество оборудования")
        @Min(value = 1, message = "Минимальное количество оборудования: 1")
        Integer quantity
) { }
