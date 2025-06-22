package org.dormhub.www.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rq.EquipmentLocationRequest;
import org.dormhub.www.controller.rest.dto.rq.SearchRequest;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rs.equipment.EquipmentLocationResponse;
import org.dormhub.www.controller.rest.mapper.EquipmentLocationWebMapper;
import org.dormhub.www.service.EquipmentLocationService;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.equipment.EquipmentLocationDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.dormhub.www.util.UrlConstants.API_EQUIPMENTS;
import static org.dormhub.www.util.UrlConstants.API_EQUIPMENT_LOCATIONS;
import static org.dormhub.www.util.UrlConstants.API_SEARCH;
import static org.dormhub.www.util.UrlConstants.BASE_API_V1;
import static org.dormhub.www.util.UrlConstants.ID_PATH;

@Slf4j
@RestController
@RequestMapping(BASE_API_V1 + API_EQUIPMENT_LOCATIONS)
@RequiredArgsConstructor
public class EquipmentLocationController {

    private final EquipmentLocationService service;
    private final EquipmentLocationWebMapper mapper;

    @PostMapping(API_SEARCH)
    public PaginationResponse<EquipmentLocationResponse> search(@RequestBody SearchRequest request) {
        SearchDto searchDto = mapper.toDto(request);
        PaginationDto<EquipmentLocationDto> paginationDto = service.search(searchDto);
        return mapper.toResponse(paginationDto);
    }


    @GetMapping(ID_PATH)
    public EquipmentLocationResponse getById(@PathVariable UUID id) {
        log.info("Received request to get equipmentLocation: {}", id);
        EquipmentLocationDto dto = service.getById(id);
        return mapper.toResponse(dto);
    }

    @PutMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@PathVariable UUID id, @RequestBody @Valid EquipmentLocationRequest request) {
        log.info("Received request to update equipmentLocation with ID: {}", id);
        EquipmentLocationDto dto = mapper.toDto(request);
        service.update(dto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public IdResponse save(@RequestBody @Valid EquipmentLocationRequest request) {
        log.info("Received request to save equipmentLocation");
        EquipmentLocationDto dto = mapper.toDto(request);
        UUID equipmentLocationId = service.save(dto);
        return mapper.toResponse(equipmentLocationId);
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable UUID id) {
        log.info("Received request to delete equipmentLocation by id: {}", id);
        service.delete(id);
    }

}
