package org.dormhub.www.controller.rest.dto.rs;

import java.util.UUID;

public record RoleResponse(
        UUID id,
        String name
) { }
