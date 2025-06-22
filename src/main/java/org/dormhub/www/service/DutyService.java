package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.service.dto.duty.DutyDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.storage.service.DutyStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DutyService {

    private final DutyStorageService storageService;

    @Transactional
    public UUID save(DutyDto dto) {
        DutyDto savedDto = storageService.save(dto);
        return savedDto.id();
    }

    @Transactional
    public void delete(UUID id) {
        storageService.delete(id);
    }

    public DutyDto getById(UUID id) {
        return storageService.getById(id);
    }

    public PaginationDto<DutyDto> search(UUID apartmentId, UUID tenantId, SearchDto searchDto) {
        return storageService.search(apartmentId, tenantId, searchDto);
    }

    @Transactional
    public void update(DutyDto dto) {
        storageService.update(dto);
    }

}
