package org.dormhub.www.service.dto.staffer;

import lombok.Builder;

import java.util.UUID;

@Builder
public record StafferBriefDto(
        UUID id,
        String lastName,
        String firstName,
        String fatherName
) { }
