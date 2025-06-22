package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.service.dto.apartment.ApartmentDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.equipment.EquipmentLocationDto;
import org.dormhub.www.storage.service.ApartmentStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApartmentService {

    private final ApartmentStorageService storageService;

    @Transactional
    public UUID save(ApartmentDto dto) {
        ApartmentDto savedDto = storageService.save(dto);
        return savedDto.id();
    }

    @Transactional
    public void delete(UUID id) {
        storageService.delete(id);
    }

    public ApartmentDto getById(UUID id) {
        return storageService.getById(id);
    }

    public PaginationDto<ApartmentDto> search(UUID dormId, boolean isFree, SearchDto searchDto) {
        return storageService.search(dormId, isFree, searchDto);
    }

    @Transactional
    public void update(ApartmentDto dto) {
        storageService.update(dto);
    }

    public PaginationDto<EquipmentLocationDto> getEquipments(UUID id, SearchDto searchDto) {
        return storageService.getEquipmentsById(id, searchDto);
    }

    public void validateRoomForCheckIn(UUID id, UUID dormId) {
        storageService.validateRoomForCheckIn(id, dormId);
    }
}
