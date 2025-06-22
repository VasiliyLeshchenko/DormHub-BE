package org.dormhub.www.controller.rest.dto.rs.user;

import java.util.UUID;

public record UserBriefResponse(
        UUID id,
        String fullName
) { }
