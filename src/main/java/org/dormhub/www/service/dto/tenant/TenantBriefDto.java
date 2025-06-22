package org.dormhub.www.service.dto.tenant;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TenantBriefDto(
        UUID id,
        String lastName,
        String firstName,
        String fatherName
) { }
