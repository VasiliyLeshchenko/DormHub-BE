package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.equipment.EquipmentLocationDto;
import org.dormhub.www.storage.service.EquipmentLocationStorageService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EquipmentLocationService {

    private final EquipmentLocationStorageService storageService;


    public PaginationDto<EquipmentLocationDto> search(SearchDto searchDto) {
        return storageService.search(searchDto);
    }

    public EquipmentLocationDto getById(UUID id) {
        return storageService.getById(id);
    }

    public UUID save(EquipmentLocationDto equipment) {
        EquipmentLocationDto savedEquipmentLocation = storageService.save(equipment);
        return savedEquipmentLocation.id();
    }

    public void update(EquipmentLocationDto dto) {
        storageService.update(dto);
    }

    public void delete(UUID id) {
        storageService.delete(id);
    }

}
