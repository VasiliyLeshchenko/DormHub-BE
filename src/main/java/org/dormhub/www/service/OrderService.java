package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.service.dto.OrderDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.storage.service.OrderStorageService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderStorageService storageService;

    public UUID save(OrderDto dto) {
        OrderDto savedDto = storageService.save(dto);
        return savedDto.id();
    }

    public void delete(UUID id) {
        storageService.delete(id);
    }

    public OrderDto getById(UUID id) {
        return storageService.getById(id);
    }

    public PaginationDto<OrderDto> search(UUID dormId, UUID tenantId, UUID stafferId, SearchDto searchDto) {
        return storageService.search(dormId, tenantId, stafferId, searchDto);
    }

    public void update(OrderDto dto) {
        storageService.update(dto);
    }

}
