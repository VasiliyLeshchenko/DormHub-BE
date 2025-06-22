package org.dormhub.www.service.dto.dorm;

import lombok.Builder;
import org.dormhub.www.storage.entity.enums.DormType;

import java.util.UUID;

@Builder
public record DormDto(
        UUID id,
        String name,
        String address,
        Integer postalCode,
        String phone,
        DormType type
) { }
