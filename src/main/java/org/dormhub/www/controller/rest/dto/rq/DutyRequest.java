package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DutyRequest(
        UUID id,

        @NotNull(message = "Отсутствует дата дежурства")
        LocalDate date,

        @NotNull(message = "Не указано помещение для дежурства")
        UUID apartmentId,

        List<UUID> tenantIds
) { }