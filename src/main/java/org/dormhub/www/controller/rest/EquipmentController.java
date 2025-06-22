package org.dormhub.www.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rq.ApartmentRequest;
import org.dormhub.www.controller.rest.dto.rq.EquipmentRequest;
import org.dormhub.www.controller.rest.dto.rq.SearchRequest;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rs.apartment.ApartmentResponse;
import org.dormhub.www.controller.rest.dto.rs.equipment.EquipmentInfoResponse;
import org.dormhub.www.controller.rest.mapper.EquipmentWebMapper;
import org.dormhub.www.controller.rest.dto.rs.equipment.EquipmentBriefResponse;
import org.dormhub.www.service.EquipmentService;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.apartment.ApartmentDto;
import org.dormhub.www.service.dto.equipment.EquipmentBriefDto;
import org.dormhub.www.service.dto.equipment.EquipmentInfoDto;
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

import java.util.List;
import java.util.UUID;

import static org.dormhub.www.util.UrlConstants.API_EQUIPMENTS;
import static org.dormhub.www.util.UrlConstants.API_SEARCH;
import static org.dormhub.www.util.UrlConstants.BASE_API_V1;
import static org.dormhub.www.util.UrlConstants.ID_PATH;

@Slf4j
@RestController
@RequestMapping(BASE_API_V1 + API_EQUIPMENTS)
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService service;
    private final EquipmentWebMapper mapper;

    @PostMapping(API_SEARCH)
    public PaginationResponse<EquipmentInfoResponse> search(
            @RequestParam(required = false) UUID dormId,
            @RequestBody SearchRequest request) {
        SearchDto searchDto = mapper.toDto(request);
        PaginationDto<EquipmentInfoDto> paginationDto = service.search(dormId, searchDto);
        return mapper.toResponse(paginationDto);
    }


    @GetMapping(ID_PATH)
    public EquipmentBriefResponse getById(@PathVariable UUID id) {
        log.info("Received request to get equipment: {}", id);
        EquipmentBriefDto dto = service.getById(id);
        return mapper.toResponse(dto);
    }

    @PutMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@PathVariable UUID id, @RequestBody @Valid EquipmentRequest request) {
        log.info("Received request to update equipment with ID: {}", id);
        EquipmentBriefDto dto = mapper.toDto(request);
        service.update(dto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public IdResponse save(@RequestBody @Valid EquipmentRequest request) {
        log.info("Received request to save equipment");
        EquipmentBriefDto dto = mapper.toDto(request);
        UUID equipmentId = service.save(dto);
        return mapper.toResponse(equipmentId);
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable UUID id) {
        log.info("Received request to delete equipment by id: {}", id);
        service.delete(id);
    }

}
