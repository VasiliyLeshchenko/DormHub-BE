package org.dormhub.www.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.TenantInfoDto;
import org.dormhub.www.service.dto.tenant.TenantDto;
import org.dormhub.www.storage.entity.TenantEntity;
import org.dormhub.www.storage.mapper.TenantStorageMapper;
import org.dormhub.www.storage.repository.TenantRepository;
import org.dormhub.www.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantStorageService {

    private static final String TENANT_NOT_FOUND = "Жилец с ID (%s) не найден";
    private final TenantRepository repository;
    private final TenantStorageMapper mapper;


    public TenantDto save(TenantInfoDto dto) {
        TenantEntity entity = mapper.toEntity(dto);
        TenantEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public TenantDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(TENANT_NOT_FOUND.formatted(id)));
    }

    public TenantInfoDto getInfoById(UUID id) {
        return repository.findById(id)
                .map(mapper::toInfoDto)
                .orElseThrow(() -> new ResourceNotFoundException(TENANT_NOT_FOUND.formatted(id)));
    }

    public PaginationDto<TenantDto> search(UUID dormId, UUID apartmentId, SearchDto searchDto) {
        Sort sort = getFullNameSort();
        Pageable page = PageRequest.of(searchDto.page(), searchDto.size(), sort);
        Page<TenantEntity> staffers;
        if (searchDto.query() == null) {
            staffers  = dormId != null || apartmentId != null
                    ? repository.search(dormId, apartmentId, page)
                    : repository.findAll(page);
        } else {
            staffers = repository.search(dormId, searchDto.query(), page);
        }

        return new PaginationDto<>(
                mapper.toListDto(staffers),
                staffers.getNumberOfElements(),
                staffers.getNumber(),
                staffers.getTotalElements()
        );
    }

    public void update(TenantInfoDto dto) {
        if (!repository.existsById(dto.getId())) {
            log.error("Denied request to update not exist entity");
            throw new IllegalArgumentException("Запрос откланен, нельзя обновить несуществующего жильца");
        }

        TenantEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private Sort getFullNameSort() {
        Sort.Order lastName = new Sort.Order(Sort.Direction.ASC, "lastName");
        Sort.Order firstName = new Sort.Order(Sort.Direction.ASC, "firstName");
        Sort.Order fatherName = new Sort.Order(Sort.Direction.ASC, "fatherName");
        return Sort.by(lastName, firstName, fatherName);
    }

}
