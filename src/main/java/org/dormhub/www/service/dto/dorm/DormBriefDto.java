package org.dormhub.www.service.dto.dorm;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DormBriefDto(
        UUID id,
        String name,
        String type,
        String address
) { }
