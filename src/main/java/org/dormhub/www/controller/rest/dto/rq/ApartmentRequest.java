package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.dormhub.www.storage.entity.enums.ApartmentType;

import java.util.List;
import java.util.UUID;

public record ApartmentRequest(
       UUID id,

       @NotNull(message = "Отсутствует номер помещения")
       @Min(value = 1, message = "Минимальный номер комнаты: 1")
       Integer number,

       @Pattern(regexp = "[А-Я]", message = "Суфикс должен быть от А до Я")
       String suffix,

       @NotNull(message = "Отсутствует тип помещения")
       ApartmentType type,

       @NotNull(message = "Не указана всестимость помещения")
       @Min(value = 0, message = "Минимальная вместимость помещения: 0")
       Integer size,

       @NotNull(message = "Не указан этаж помещения")
       @Min(value = 0, message = "Минимальный этаж: 0")
       Integer floor,

       @NotNull(message = "Не указано общежитие")
       UUID dormId,

       UUID parentId,

       List<@Valid ApartmentRequest> children
) { }
