package org.dormhub.www.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.exception.ResourceNotFoundException;
import org.dormhub.www.service.dto.duty.DutyDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.storage.entity.DutyEntity;
import org.dormhub.www.storage.mapper.DutyStorageMapper;
import org.dormhub.www.storage.repository.DutyRepository;
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
public class DutyStorageService {

    private static final String APARTMENT_NOT_FOUND = "Дежурство с ID (%s) не найдено";
    private final DutyRepository repository;
    private final DutyStorageMapper mapper;

    public DutyDto save(DutyDto dto) {
        DutyEntity entity = mapper.toEntity(dto);
        DutyEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public DutyDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(APARTMENT_NOT_FOUND.formatted(id)));
    }

    public PaginationDto<DutyDto> search(UUID apartmentId, UUID tenantId, SearchDto searchDto) {
        Sort sort = PageUtils.singleFieldSort(searchDto.order());
        Pageable page = PageRequest.of(searchDto.page(), searchDto.size(), sort);
        Page<DutyEntity> duties = apartmentId != null || tenantId != null
                ? repository.search(apartmentId, tenantId, page)
                : repository.findAll(page);
        return toDto(duties);
    }

    public void update(DutyDto dto) {
        if (!repository.existsById(dto.id())) {
            log.error("Denied request to update not exist entity");
            throw new IllegalArgumentException("Запрос откланен, нельзя обновить несуществующее дежурство");
        }

        DutyEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    private PaginationDto<DutyDto> toDto(Page<DutyEntity> page) {
        return new PaginationDto<>(
                mapper.toDto(page.getContent()),
                page.getNumberOfElements(),
                page.getNumber(),
                page.getTotalElements()
        );
    }

}
