package org.dormhub.www.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.service.dto.dorm.DormBriefDto;
import org.dormhub.www.service.dto.dorm.DormDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.storage.entity.DormEntity;
import org.dormhub.www.storage.entity.EquipmentEntity;
import org.dormhub.www.storage.mapper.DormStorageMapper;
import org.dormhub.www.storage.repository.DormRepository;
import org.dormhub.www.util.PageUtils;
import org.dormhub.www.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DormStorageService {

    private static final String DORM_NOT_FOUND = "Общежитие с ID (%s) не найдено";
    private final DormRepository repository;
    private final DormStorageMapper mapper;

    public List<DormBriefDto> getBriefAll() {
        List<DormEntity> dorms = repository.findAll(Sort.by(new Sort.Order(Sort.Direction.ASC, "name")));
        return mapper.toBriefDto(dorms);
    }

    public DormDto save(DormDto dto) {
        DormEntity entity = mapper.toEntity(dto);
        DormEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public DormDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(DORM_NOT_FOUND.formatted(id)));
    }

    public PaginationDto<DormDto> search(SearchDto searchDto) {
        Sort sort = PageUtils.singleFieldSort(searchDto.order());
        Pageable page = PageRequest.of(searchDto.page(), searchDto.size(), sort);
        Page<DormEntity> dorms;

        if (searchDto.query() == null) {
            dorms = repository.findAll(page);
        } else {
            dorms = repository.search(searchDto.query(), page);
        }
        return toDto(dorms);
    }

    public void update(DormDto dto) {
        if (!repository.existsById(dto.id())) {
            log.error("Denied request to update not exist entity");
            throw new IllegalArgumentException("Запрос откланен, нельзя обновить несуществующее общежитие");
        }

        DormEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    private PaginationDto<DormDto> toDto(Page<DormEntity> page) {
        return new PaginationDto<>(
                mapper.toDto(page.getContent()),
                page.getNumberOfElements(),
                page.getNumber(),
                page.getTotalElements()
        );
    }

}
