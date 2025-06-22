package org.dormhub.www.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rq.DutyRequest;
import org.dormhub.www.controller.rest.dto.rq.SearchRequest;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.controller.rest.dto.rs.duty.DutyResponse;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.mapper.DutyWebMapper;
import org.dormhub.www.service.DutyService;
import org.dormhub.www.service.dto.duty.DutyDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
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

import static org.dormhub.www.util.UrlConstants.API_DUTIES;
import static org.dormhub.www.util.UrlConstants.API_SEARCH;
import static org.dormhub.www.util.UrlConstants.BASE_API_V1;
import static org.dormhub.www.util.UrlConstants.ID_PATH;

@Slf4j
@RestController
@RequestMapping(BASE_API_V1 + API_DUTIES)
@RequiredArgsConstructor
public class DutyController {

    private final DutyService service;
    private final DutyWebMapper mapper;

    @PostMapping(API_SEARCH)
    public PaginationResponse<DutyResponse> search(
            @RequestParam(required = false) UUID apartmentId,
            @RequestParam(required = false) UUID tenantId,
            @RequestBody SearchRequest request) {
        SearchDto searchDto = mapper.toDto(request);
        PaginationDto<DutyDto> paginationDto = service.search(apartmentId, tenantId, searchDto);
        return mapper.toResponse(paginationDto);
    }

    @GetMapping(ID_PATH)
    public DutyResponse getById(@PathVariable UUID id) {
        log.info("Received request to get duty: {}", id);
        DutyDto dto = service.getById(id);
        return mapper.toResponse(dto);
    }

    @PostMapping
    public IdResponse save(@RequestBody @Valid DutyRequest request) {
        log.info("Received request to save duty: {}", request);
        DutyDto dto = mapper.toDto(request);
        UUID dormId = service.save(dto);
        return mapper.toResponse(dormId);
    }

    @PutMapping(ID_PATH)
    public void update(@PathVariable UUID id, @RequestBody @Valid DutyRequest request) {
        log.info("Received request to update duty with ID: {}", id);
        DutyDto dto = mapper.toDto(request);
        service.update(dto);
    }

    @DeleteMapping(ID_PATH)
    public void delete(@PathVariable UUID id) {
        log.info("Received request to delete duty by id: {}", id);
        service.delete(id);
    }

}
