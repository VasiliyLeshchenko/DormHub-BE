package org.dormhub.www.controller.rest.dto.rs.duty;

import org.dormhub.www.controller.rest.dto.rs.apartment.ApartmentBriefResponse;
import org.dormhub.www.controller.rest.dto.rs.tenant.TenantResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DutyResponse(
        UUID id,
        LocalDate date,
        ApartmentBriefResponse apartment,
        List<TenantResponse> tenants
) { }
