package org.dormhub.www.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rq.OrderRequest;
import org.dormhub.www.controller.rest.dto.rq.SearchRequest;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.controller.rest.dto.rs.OrderResponse;
import org.dormhub.www.controller.rest.dto.rs.PaginationResponse;
import org.dormhub.www.controller.rest.mapper.OrderWebMapper;
import org.dormhub.www.service.OrderService;
import org.dormhub.www.service.dto.OrderDto;
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

import static org.dormhub.www.util.UrlConstants.API_ORDERS;
import static org.dormhub.www.util.UrlConstants.API_SEARCH;
import static org.dormhub.www.util.UrlConstants.BASE_API_V1;
import static org.dormhub.www.util.UrlConstants.ID_PATH;

@Slf4j
@RestController
@RequestMapping(BASE_API_V1 + API_ORDERS)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;
    private final OrderWebMapper mapper;

    @PostMapping(API_SEARCH)
    public PaginationResponse<OrderResponse> search(
            @RequestParam(required = false) UUID dormId,
            @RequestParam(required = false) UUID tenantId,
            @RequestParam(required = false) UUID stafferId,
            @RequestBody SearchRequest request) {
        SearchDto searchDto = mapper.toDto(request);
        PaginationDto<OrderDto> paginationDto = service.search(dormId, tenantId, stafferId, searchDto);
        return mapper.toResponse(paginationDto);
    }

    @GetMapping(ID_PATH)
    public OrderResponse getById(@PathVariable UUID id) {
        log.info("Received request to get order: {}", id);
        OrderDto dto = service.getById(id);
        return mapper.toResponse(dto);
    }

    @PostMapping
    public IdResponse save(@RequestBody @Valid OrderRequest request) {
        log.info("Received request to save order: {}", request);
        OrderDto dto = mapper.toDto(request);
        UUID orderId = service.save(dto);
        return mapper.toResponse(orderId);
    }

    @PutMapping(ID_PATH)
    public void update(@PathVariable UUID id, @RequestBody @Valid OrderRequest request) {
        log.info("Received request to update order with ID: {}", id);
        OrderDto dto = mapper.toDto(request);
        service.update(dto);
    }

    @DeleteMapping(ID_PATH)
    public void delete(@PathVariable UUID id) {
        log.info("Received request to delete order by id: {}", id);
        service.delete(id);
    }

}
