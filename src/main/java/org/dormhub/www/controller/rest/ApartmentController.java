package org.dormhub.www.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rq.SearchRequest;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rq.ApartmentRequest;
import org.dormhub.www.controller.rest.dto.rs.apartment.ApartmentResponse;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.controller.rest.dto.rs.equipment.EquipmentInfoResponse;
import org.dormhub.www.controller.rest.dto.rs.equipment.EquipmentLocationResponse;
import org.dormhub.www.controller.rest.mapper.ApartmentWebMapper;
import org.dormhub.www.controller.rest.mapper.EquipmentLocationWebMapper;
import org.dormhub.www.service.ApartmentService;
import org.dormhub.www.service.dto.apartment.ApartmentDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.equipment.EquipmentInfoDto;
import org.dormhub.www.service.dto.equipment.EquipmentLocationDto;
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

import static org.dormhub.www.util.UrlConstants.API_APARTMENTS;
import static org.dormhub.www.util.UrlConstants.API_EQUIPMENTS;
import static org.dormhub.www.util.UrlConstants.API_SEARCH;
import static org.dormhub.www.util.UrlConstants.BASE_API_V1;
import static org.dormhub.www.util.UrlConstants.ID_PATH;

@Slf4j
@RestController
@RequestMapping(BASE_API_V1 + API_APARTMENTS)
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentService service;
    private final ApartmentWebMapper apartmentWebMapper;
    private final EquipmentLocationWebMapper equipmentLocationWebMapper;


    @PostMapping(API_SEARCH)
    public PaginationResponse<ApartmentResponse> search(
            @RequestParam(required = false) UUID dormId,
            @RequestParam(required = false, defaultValue = "false") boolean isFree,
            @RequestBody SearchRequest request) {
        SearchDto searchDto = apartmentWebMapper.toDto(request);
        PaginationDto<ApartmentDto> paginationDto = service.search(dormId, isFree, searchDto);
        return apartmentWebMapper.toResponse(paginationDto);
    }

    @GetMapping(ID_PATH)
    public ApartmentResponse getById(@PathVariable UUID id) {
        log.info("Received request to get apartment: {}", id);
        ApartmentDto dto = service.getById(id);
        return apartmentWebMapper.toResponse(dto);
    }

    @PostMapping(ID_PATH + API_EQUIPMENTS)
    public PaginationResponse<EquipmentLocationResponse> getEquipmentsById(
            @PathVariable UUID id,
            @RequestBody SearchRequest request
    ) {
        log.info("Received request to get equipment for apartment: {}", id);
        SearchDto searchDto = apartmentWebMapper.toDto(request);
        PaginationDto<EquipmentLocationDto> dto = service.getEquipments(id, searchDto);
        return equipmentLocationWebMapper.toResponse(dto);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public IdResponse save(@RequestBody @Valid ApartmentRequest request) {
        log.info("Received request to save apartment: {}", request);
        ApartmentDto dto = apartmentWebMapper.toDto(request);
        UUID dormId = service.save(dto);
        return apartmentWebMapper.toResponse(dormId);
    }

    @PutMapping(ID_PATH)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void update(@PathVariable UUID id, @RequestBody @Valid ApartmentRequest request) {
        log.info("Received request to update apartment with ID: {}", id);
        ApartmentDto dto = apartmentWebMapper.toDto(request);
        service.update(dto);
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void delete(@PathVariable UUID id) {
        log.info("Received request to delete apartment by id: {}", id);
        service.delete(id);
    }

}
