package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.dormhub.www.storage.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderRequest(
    UUID id,

    @NotNull(message = "Отсутствует время создания")
    LocalDateTime createdAt,

    @NotBlank(message = "Отсутствует заголовок")
    String title,
    String description,

    @NotNull(message = "Отсутствует статус")
    OrderStatus status,
    UUID creatorId,
    UUID assignedId

) { }
