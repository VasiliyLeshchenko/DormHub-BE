package org.dormhub.www.controller.rest.dto.rs.tenant;

import java.util.UUID;

public record TenantBriefResponse(
        UUID id,
        String lastName,
        String firstName,
        String fatherName
) { }
