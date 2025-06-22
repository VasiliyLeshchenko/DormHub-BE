package org.dormhub.www.controller.rest.dto.rs.dorm;

import java.util.UUID;

public record DormResponse(
        UUID id,
        String name,
        String address,
        Integer postalCode,
        String phone,
        String type
) { }
