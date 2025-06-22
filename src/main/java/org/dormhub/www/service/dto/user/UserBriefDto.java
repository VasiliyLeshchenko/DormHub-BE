package org.dormhub.www.service.dto.user;

import java.util.UUID;

public record UserBriefDto(
        UUID id,
        String fullName,
        String lastName,
        String firstName,
        String fatherName
) { }
