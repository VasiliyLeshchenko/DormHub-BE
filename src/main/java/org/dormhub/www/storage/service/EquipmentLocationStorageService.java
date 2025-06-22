package org.dormhub.www.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.exception.ResourceNotFoundException;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.equipment.EquipmentLocationDto;
import org.dormhub.www.storage.entity.EquipmentLocationEntity;
import org.dormhub.www.storage.mapper.EquipmentLocationStorageMapper;
import org.dormhub.www.storage.repository.EquipmentLocationRepository;
import org.dormhub.www.util.PageUtils;
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
public class EquipmentLocationStorageService {

    private final EquipmentLocationRepository repository;
    private final EquipmentLocationStorageMapper mapper;

    public EquipmentLocationDto save(EquipmentLocationDto dto) {
        EquipmentLocationEntity entity = mapper.toEntity(dto);
        EquipmentLocationEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public PaginationDto<EquipmentLocationDto> search(SearchDto searchDto) {
        Sort sort = PageUtils.singleFieldSort(searchDto.order());
        Pageable page = PageRequest.of(searchDto.page(), searchDto.size(), sort);
        Page<EquipmentLocationEntity> equipments = repository.findAll(page);
        return mapper.toDto(equipments);
    }

    public void update(EquipmentLocationDto dto) {
        if (!repository.existsById(dto.id())) {
            log.error("Denied request to update not exist entity");
            throw new IllegalArgumentException("Запрос откланен, нельзя обновить несуществующую локацию оборудования");
        }

        EquipmentLocationEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    public EquipmentLocationDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Локация оборудования с ID (%s) не найдено".formatted(id)));

    }
}
