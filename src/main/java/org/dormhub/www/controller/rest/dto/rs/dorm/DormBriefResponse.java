package org.dormhub.www.controller.rest.dto.rs.dorm;

import java.util.UUID;

public record DormBriefResponse(
        UUID id,
        String name,
        String type,
        String address
) { }
