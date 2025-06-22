package org.dormhub.www.controller.rest.dto.rs.apartment;

import org.dormhub.www.storage.entity.enums.ApartmentType;

import java.util.UUID;

public record ApartmentBriefResponse(
       UUID id,
       Integer number,
       Character suffix,
       ApartmentType type,
       Integer floor
) { }
