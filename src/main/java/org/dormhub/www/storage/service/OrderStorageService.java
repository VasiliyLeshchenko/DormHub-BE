package org.dormhub.www.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.exception.ResourceNotFoundException;
import org.dormhub.www.service.dto.OrderDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.storage.entity.OrderEntity;
import org.dormhub.www.storage.mapper.OrderStorageMapper;
import org.dormhub.www.storage.repository.OrderRepository;
import org.dormhub.www.util.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderStorageService {

    private static final String APARTMENT_NOT_FOUND = "Заявка с ID (%s) не найдена";
    private final OrderRepository repository;
    private final OrderStorageMapper mapper;

    public OrderDto save(OrderDto dto) {
        OrderEntity entity = mapper.toEntity(dto);
        OrderEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public OrderDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(APARTMENT_NOT_FOUND.formatted(id)));
    }

    public PaginationDto<OrderDto> search(UUID dormId, UUID tenantId, UUID stafferId, SearchDto searchDto) {
        Sort sort = PageUtils.singleFieldSort(searchDto.order());
        Pageable page = PageRequest.of(searchDto.page(), searchDto.size(), sort);
        Page<OrderEntity> orders = dormId != null
                ? repository.search(dormId, page)
                : repository.search(tenantId, stafferId, page);
        return toDto(orders);
    }

    public void update(OrderDto dto) {
        if (!repository.existsById(dto.id())) {
            log.error("Denied request to update not exist entity");
            throw new IllegalArgumentException("Запрос откланен, нельзя обновить несуществующую заявку");
        }

        OrderEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    private PaginationDto<OrderDto> toDto(Page<OrderEntity> page) {
        return new PaginationDto<>(
                mapper.toDto(page.getContent()),
                page.getNumberOfElements(),
                page.getNumber(),
                page.getTotalElements()
        );
    }

}
