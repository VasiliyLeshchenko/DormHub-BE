package org.dormhub.www.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rq.SearchRequest;
import org.dormhub.www.controller.rest.dto.rq.StafferRequest;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.mapper.StafferWebMapper;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.controller.rest.dto.rs.staffer.StafferInfoResponse;
import org.dormhub.www.controller.rest.dto.rs.staffer.StafferResponse;
import org.dormhub.www.service.StafferService;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.staffer.StafferDto;
import org.dormhub.www.service.dto.staffer.StafferInfoDto;
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
import static org.dormhub.www.util.UrlConstants.API_STAFFERS;
import static org.dormhub.www.util.UrlConstants.BASE_API_V1;
import static org.dormhub.www.util.UrlConstants.ID_PATH;
import static org.dormhub.www.util.UrlConstants.INFO;

@Slf4j
@RestController
@RequestMapping(BASE_API_V1 + API_STAFFERS)
@RequiredArgsConstructor
public class StafferController {

    private final StafferService service;
    private final StafferWebMapper mapper;

    @PostMapping(API_SEARCH)
    public PaginationResponse<StafferResponse> search(
            @RequestParam(required = false) UUID dormId,
            @RequestBody SearchRequest request) {
        SearchDto searchDto = mapper.toDto(request);
        PaginationDto<StafferDto> paginationDto = service.search(dormId, searchDto);
        return mapper.toResponse(paginationDto);
    }

    @GetMapping(ID_PATH)
    public StafferResponse getById(@PathVariable UUID id) {
        log.info("Received request to get staffer: {}", id);
        StafferDto dto = service.getById(id);
        return mapper.toResponse(dto);
    }

    @GetMapping(ID_PATH + INFO)
    @PreAuthorize("hasRole('ADMIN')")
    public StafferInfoResponse getInfoById(@PathVariable UUID id) {
        log.info("Received request to get staffer info: {}", id);
        StafferInfoDto dto = service.getInfoById(id);
        return mapper.toInfoResponse(dto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public IdResponse save(@RequestBody @Valid StafferRequest request) {
        log.info("Received request to save staffer: {}", request.getCredential().login());
        StafferInfoDto dto = mapper.toInfoDto(request);
        UUID stafferId = service.save(dto);
        return new IdResponse(stafferId);
    }

    @PutMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@PathVariable UUID id, @RequestBody @Valid StafferRequest request) {
        log.info("Received request to update staffer with ID: {}", id);
        StafferInfoDto dto = mapper.toInfoDto(request);
        service.update(dto);
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable UUID id) {
        log.info("Received request to delete staffer with ID: {}", id);
        service.delete(id);
    }

}
