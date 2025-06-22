package org.dormhub.www.service.dto.duty;

import org.dormhub.www.service.dto.apartment.ApartmentBriefDto;
import org.dormhub.www.service.dto.tenant.TenantDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record DutyDto(
        UUID id,
        LocalDate date,
        ApartmentBriefDto apartment,
        List<TenantDto> tenants
) { }