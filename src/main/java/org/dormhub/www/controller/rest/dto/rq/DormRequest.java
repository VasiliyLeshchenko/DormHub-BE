package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.dormhub.www.storage.entity.enums.DormType;

import java.util.UUID;

public record DormRequest(
        UUID id,

        @NotBlank(message = "Не указано название общежития")
        String name,

        @NotBlank(message = "Не указан адрес общежития")
        String address,

        @Max(value = 999999, message = "Почтовый индекс должен содержать не больше 6 чисел")
        Integer postalCode,

        @NotBlank(message = "Не указан телефон общежития")
        String phone,

        @NotNull(message = "Не указ нип общежития")
        DormType type
) { }
