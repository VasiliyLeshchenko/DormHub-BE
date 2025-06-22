package org.dormhub.www.controller.rest.dto.rs;

import org.dormhub.www.controller.rest.dto.rs.staffer.StafferBriefResponse;
import org.dormhub.www.controller.rest.dto.rs.tenant.TenantBriefResponse;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        LocalDateTime createdAt,
        String title,
        String description,
        String status,
        TenantBriefResponse creator,
        StafferBriefResponse assigned
) { }
