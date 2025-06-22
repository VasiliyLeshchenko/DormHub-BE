package org.dormhub.www.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rq.SearchRequest;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rs.tenant.TenantInfoResponse;
import org.dormhub.www.controller.rest.dto.rs.tenant.TenantResponse;
import org.dormhub.www.controller.rest.mapper.TenantWebMapper;
import org.dormhub.www.controller.rest.dto.rq.TenantRequest;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.service.TenantService;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.TenantInfoDto;
import org.dormhub.www.service.dto.tenant.TenantDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.dormhub.www.util.UrlConstants.API_SEARCH;
import static org.dormhub.www.util.UrlConstants.API_TENANTS;
import static org.dormhub.www.util.UrlConstants.BASE_API_V1;
import static org.dormhub.www.util.UrlConstants.ID_PATH;
import static org.dormhub.www.util.UrlConstants.INFO;

@Slf4j
@RestController
@RequestMapping(BASE_API_V1 + API_TENANTS)
@RequiredArgsConstructor
public class TenantController {

    private final TenantService service;
    private final TenantWebMapper mapper;

    @PostMapping(API_SEARCH)
    public PaginationResponse<TenantResponse> search(
            @RequestParam(required = false) UUID dormId,
            @RequestParam(required = false) UUID apartmentId,
            @RequestBody SearchRequest request) {
        SearchDto searchDto = mapper.toDto(request);
        PaginationDto<TenantDto> paginationDto = service.search(dormId, apartmentId, searchDto);
        return mapper.toResponse(paginationDto);
    }

    @GetMapping(ID_PATH)
    public TenantResponse getById(@PathVariable UUID id) {
        log.info("Received request to get tenant: {}", id);
        TenantDto dto = service.getById(id);
        return mapper.toResponse(dto);
    }

    @GetMapping(ID_PATH + INFO)
    @PreAuthorize("hasRole('ADMIN')")
    public TenantInfoResponse getInfoById(@PathVariable UUID id) {
        log.info("Received request to get tenant info: {}", id);
        TenantInfoDto dto = service.getInfoById(id);
        return mapper.toInfoResponse(dto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public IdResponse save(@RequestBody @Valid TenantRequest request) {
        TenantInfoDto dto = mapper.toInfoDto(request);
        UUID tenantId = service.save(dto);
        return new IdResponse(tenantId);
    }

    @PutMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@PathVariable UUID id, @RequestBody @Valid TenantRequest request) {
        log.info("Received request to update tenant with ID: {}", id);
        TenantInfoDto dto = mapper.toInfoDto(request);
        service.update(dto);
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable UUID id) {
        log.info("Received request to delete tenant with ID: {}", id);
        service.delete(id);
    }

}
