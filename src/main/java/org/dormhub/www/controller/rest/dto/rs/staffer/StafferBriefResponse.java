package org.dormhub.www.controller.rest.dto.rs.staffer;

import java.util.UUID;

public record StafferBriefResponse(
        UUID id,
        String lastName,
        String firstName,
        String fatherName
) { }
