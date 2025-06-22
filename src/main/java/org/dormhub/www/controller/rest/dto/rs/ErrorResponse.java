package org.dormhub.www.controller.rest.dto.rs;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int status,
        String message
) { }
