package org.dormhub.www.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rq.DormRequest;
import org.dormhub.www.controller.rest.dto.rq.SearchRequest;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.dto.rs.dorm.DormBriefResponse;
import org.dormhub.www.controller.rest.dto.rs.dorm.DormResponse;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.controller.rest.mapper.DormWebMapper;
import org.dormhub.www.service.DormService;
import org.dormhub.www.service.dto.dorm.DormBriefDto;
import org.dormhub.www.service.dto.dorm.DormDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.dormhub.www.util.UrlConstants.API_DORMS;
import static org.dormhub.www.util.UrlConstants.API_SEARCH;
import static org.dormhub.www.util.UrlConstants.BASE_API_V1;
import static org.dormhub.www.util.UrlConstants.BRIEF;
import static org.dormhub.www.util.UrlConstants.ID_PATH;

@Slf4j
@RestController
@RequestMapping(BASE_API_V1 + API_DORMS)
@RequiredArgsConstructor
public class DormController {

    private final DormService service;
    private final DormWebMapper mapper;

    @GetMapping(BRIEF)
    public List<DormBriefResponse> getBriefAll() {
        List<DormBriefDto> dorms = service.getBriefAll();
        return mapper.toBriefResponse(dorms);
    }

    @PostMapping(API_SEARCH)
    public PaginationResponse<DormResponse> search(@RequestBody SearchRequest request) {
        SearchDto searchDto = mapper.toDto(request);
        PaginationDto<DormDto> paginationDto = service.search(searchDto);
        return mapper.toResponse(paginationDto);
    }

    @GetMapping(ID_PATH)
    public DormResponse getById(@PathVariable UUID id) {
        log.info("Received request to get dorm: {}", id);
        DormDto dto = service.getById(id);
        return mapper.toResponse(dto);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public IdResponse save(@RequestBody @Valid DormRequest request) {
        log.info("Received request to save dorm: {}", request);
        DormDto dto = mapper.toDto(request);
        UUID dormId = service.save(dto);
        return mapper.toResponse(dormId);
    }

    @PutMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@PathVariable UUID id, @RequestBody @Valid DormRequest request) {
        log.info("Received request to update dorm with ID: {}", id);
        DormDto dto = mapper.toDto(request);
        service.update(dto);
    }

    @DeleteMapping(ID_PATH)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable UUID id) {
        log.info("Received request to delete dorm by id: {}", id);
        service.delete(id);
    }

}
