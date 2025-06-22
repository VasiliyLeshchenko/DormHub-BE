package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.equipment.EquipmentBriefDto;
import org.dormhub.www.service.dto.equipment.EquipmentInfoDto;
import org.dormhub.www.storage.service.EquipmentStorageService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentStorageService storageService;


    public PaginationDto<EquipmentInfoDto> search(UUID dormId, SearchDto searchDto) {
        return storageService.search(dormId, searchDto);
    }

    public EquipmentBriefDto getById(UUID id) {
        return storageService.getById(id);
    }

    public UUID save(EquipmentBriefDto equipment) {
        EquipmentBriefDto savedEquipment = storageService.save(equipment);
        return savedEquipment.id();
    }

    public void update(EquipmentBriefDto dto) {
        storageService.update(dto);
    }

    public void delete(UUID id) {
        storageService.delete(id);
    }

}
