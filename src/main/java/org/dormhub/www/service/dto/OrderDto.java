package org.dormhub.www.service.dto;

import org.dormhub.www.service.dto.staffer.StafferBriefDto;
import org.dormhub.www.service.dto.tenant.TenantBriefDto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderDto(
        UUID id,
        LocalDateTime createdAt,
        String title,
        String description,
        String status,
        TenantBriefDto creator,
        StafferBriefDto assigned
) { }