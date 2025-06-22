package org.dormhub.www.controller.rest.dto.rs.apartment;

import org.dormhub.www.controller.rest.dto.rs.dorm.DormBriefResponse;
import org.dormhub.www.storage.entity.enums.ApartmentType;

import java.util.List;
import java.util.UUID;

public record ApartmentResponse(
       UUID id,
       Integer number,
       Character suffix,
       ApartmentType type,
       Integer size,
       Integer floor,
       DormBriefResponse dorm,
       ApartmentBriefResponse parent,
       List<ApartmentResponse> children
) { }
