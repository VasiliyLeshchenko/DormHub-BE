package org.dormhub.www.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.service.dto.apartment.ApartmentDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.equipment.EquipmentLocationDto;
import org.dormhub.www.storage.entity.ApartmentEntity;
import org.dormhub.www.storage.entity.EquipmentLocationEntity;
import org.dormhub.www.storage.entity.enums.ApartmentType;
import org.dormhub.www.storage.mapper.ApartmentStorageMapper;
import org.dormhub.www.storage.mapper.EquipmentLocationStorageMapper;
import org.dormhub.www.storage.repository.ApartmentRepository;
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
public class ApartmentStorageService {

    private static final String APARTMENT_NOT_FOUND = "Помещение с ID (%s) не найдено";
    private final ApartmentRepository repository;
    private final ApartmentStorageMapper apartmentMapper;
    private final EquipmentLocationStorageMapper equipmentMapper;

    public ApartmentDto save(ApartmentDto dto) {
        validate(dto);
        ApartmentEntity entity = apartmentMapper.toEntity(dto);
        ApartmentEntity savedEntity = repository.save(entity);
        return apartmentMapper.toDto(savedEntity);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public ApartmentDto getById(UUID id) {
        return repository.findById(id)
                .map(apartmentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(APARTMENT_NOT_FOUND.formatted(id)));
    }

    public PaginationDto<ApartmentDto> search(UUID dormId, boolean isFree, SearchDto searchDto) {
        Sort sort = PageUtils.singleFieldSort(searchDto.order());
        Pageable page = PageRequest.of(searchDto.page(), searchDto.size(), sort);
        if (searchDto.query() == null) {
            if (isFree) {
                Page<ApartmentEntity> apartments = repository.findWithFreeSpase(dormId, ApartmentType.ROOM, page);
                return apartmentMapper.toDto(apartments);
            } else {
                Page<UUID> ids = repository.findIdsWithoutParent(dormId, page);
                List<ApartmentEntity> apartments = repository.findWithChildrenAndDormByIds(ids.toList(), sort);
                return apartmentMapper.toPaginationDto(apartments, ids.getNumberOfElements(), ids.getNumber(), ids.getTotalElements());
            }
        } else {
            Page<ApartmentEntity> apartments = repository.search(dormId, searchDto.query(), page);
            return apartmentMapper.toDto(apartments);
        }
    }

    public void update(ApartmentDto dto) {
        ApartmentEntity entity = repository.findById(dto.id())
                        .orElseThrow(() -> new ResourceNotFoundException(APARTMENT_NOT_FOUND.formatted(dto.id())));
        validate(dto);
        ApartmentEntity apartment = apartmentMapper.toEntity(dto);
        if (entity.getTenants().size() > apartment.getSize()) {
            throw new IllegalArgumentException("Вместимость помещения меньше, чем количество жильцов");
        }
        repository.save(apartment);
    }

    public PaginationDto<EquipmentLocationDto> getEquipmentsById(UUID id, SearchDto searchDto) {
        Pageable page = PageRequest.of(searchDto.page(), searchDto.size());
        Page<EquipmentLocationEntity> equipmentLocations = repository.getEquipments(id, page);
        return equipmentMapper.toDto(equipmentLocations);
    }

    private void validate(ApartmentDto apartment) {
        if (apartment.parent() != null) {
            ApartmentDto parent = getById(apartment.parent().id());

            if (!apartment.number().equals(parent.number())) {
                throw new IllegalArgumentException("Дочернее помещение должно иметь идентичный номер с родительским");
            }

            if (apartment.type() == ApartmentType.ROOM && parent.type() != ApartmentType.BLOCK) {
                throw new IllegalArgumentException("Комната может быть дочерним помещением только для помещения типа: Блок");
            }

            if (apartment.type() == ApartmentType.BLOCK && parent.type() != ApartmentType.BLOCK) {
                throw new IllegalArgumentException("Блок может быть дочерним помещением только для помещения типа: Блок");
            }

            if (!parent.dorm().id().equals(apartment.dorm().id())) {
                throw new IllegalArgumentException("У корневого помещения и дочерних помещений должно быть идентичное общежитие");
            }

            if (!parent.floor().equals(apartment.floor())) {
                throw new IllegalArgumentException("У корневого помещения и дочерних помещений должен быть идентичный этаж");
            }
        }

        if (apartment.type() == ApartmentType.ROOM && !apartment.children().isEmpty()) {
            throw new IllegalArgumentException("У комнаты не может быть дочерних помещений");
        }

        if (apartment.type() == ApartmentType.KITCHEN && !apartment.children().isEmpty()) {
            throw new IllegalArgumentException("У кухни не может быть дочерних помещений");
        }

        apartment.children().forEach(this::validate);
    }

    public void validateRoomForCheckIn(UUID id, UUID dormId) {
        ApartmentEntity apartment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(APARTMENT_NOT_FOUND.formatted(id)));

        if (apartment.getType() != ApartmentType.ROOM) {
            throw new IllegalArgumentException("Жильца можно заселить только в помещение типа: Комната");
        }

        if (apartment.getSize() <= apartment.getTenants().size()) {
            throw new IllegalArgumentException("В комнате недостаточно свободных коек");
        }

        if (!apartment.getDorm().getId().equals(dormId)) {
            throw new IllegalArgumentException("Общежитие комнаты и жильца различаются");
        }
    }
}
