package org.dormhub.www.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.staffer.StafferDto;
import org.dormhub.www.service.dto.staffer.StafferInfoDto;
import org.dormhub.www.storage.entity.StafferEntity;
import org.dormhub.www.storage.mapper.StafferStorageMapper;
import org.dormhub.www.storage.repository.StafferRepository;
import org.dormhub.www.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StafferStorageService {

    private static final String STAFFER_NOT_FOUND = "Сотрудник с ID (%s) не найден";
    private final StafferRepository repository;
    private final StafferStorageMapper mapper;

    public StafferInfoDto save(StafferInfoDto dto) {
        StafferEntity entity = mapper.toEntity(dto);
        StafferEntity savedEntity = repository.save(entity);
        return mapper.toInfoDto(savedEntity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public void update(StafferInfoDto dto) {
        if (!repository.existsById(dto.getId())) {
            log.error("Denied request to update not exist entity");
            throw new IllegalArgumentException("Запрос откланен, нельзя обновить несуществующего сотрудника");
        }

        StafferEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    public StafferDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(STAFFER_NOT_FOUND.formatted(id)));
    }

    public StafferInfoDto getInfoById(UUID id) {
        return repository.findById(id)
                .map(mapper::toInfoDto)
                .orElseThrow(() -> new ResourceNotFoundException(STAFFER_NOT_FOUND.formatted(id)));
    }

    public PaginationDto<StafferDto> search(UUID dormId, SearchDto searchDto) {
        Sort sort = getFullNameSort();
        Pageable page = PageRequest.of(searchDto.page(), searchDto.size(), sort);
        Page<StafferEntity> staffers;
        if (searchDto.query() == null) {
            staffers = dormId != null
                    ? repository.search(dormId, page)
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

    private Sort getFullNameSort() {
        Order lastName = new Order(Sort.Direction.ASC, "lastName");
        Order firstName = new Order(Sort.Direction.ASC, "firstName");
        Order fatherName = new Order(Sort.Direction.ASC, "fatherName");
        return Sort.by(lastName, firstName, fatherName);
    }
}
