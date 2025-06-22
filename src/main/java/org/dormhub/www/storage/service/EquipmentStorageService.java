package org.dormhub.www.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.exception.ResourceNotFoundException;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.equipment.EquipmentBriefDto;
import org.dormhub.www.service.dto.equipment.EquipmentInfoDto;
import org.dormhub.www.storage.entity.EquipmentEntity;
import org.dormhub.www.storage.mapper.EquipmentStorageMapper;
import org.dormhub.www.storage.repository.EquipmentRepository;
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
public class EquipmentStorageService {

    private final EquipmentRepository repository;
    private final EquipmentStorageMapper mapper;

    public PaginationDto<EquipmentInfoDto> search(UUID dormId, SearchDto searchDto) {
        Sort sort = PageUtils.singleFieldSort(searchDto.order());
        Pageable page = PageRequest.of(searchDto.page(), searchDto.size(), sort);
        Page<EquipmentInfoDto> equipments;
        if (searchDto.query() == null) {
            equipments = dormId != null
                    ? repository.search(dormId, page)
                    : repository.search(page);
        } else {
            Page<EquipmentEntity> entities = repository.search(searchDto.query(), page);
            return mapper.toInfoDto(entities);
        }

        return mapper.toDto(equipments);
    }


    public EquipmentBriefDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Оборудование с (%s) not found".formatted(id)));

    }
    public EquipmentBriefDto save(EquipmentBriefDto dto) {
        EquipmentEntity entity = mapper.toEntity(dto);
        EquipmentEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public void update(EquipmentBriefDto dto) {
        if (!repository.existsById(dto.id())) {
            log.error("Denied request to update not exist entity");
            throw new IllegalArgumentException("Запрос откланен, нельзя обновить несуществующее оборудование");
        }

        EquipmentEntity entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
