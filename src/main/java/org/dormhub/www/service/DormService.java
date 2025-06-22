package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.service.dto.dorm.DormBriefDto;
import org.dormhub.www.service.dto.dorm.DormDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.storage.service.DormStorageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DormService {

    private final DormStorageService storageService;

    public UUID save(DormDto dto) {
        DormDto savedDto = storageService.save(dto);
        return savedDto.id();
    }

    public void delete(UUID id) {
        storageService.delete(id);
    }

    public DormDto getById(UUID id) {
        return storageService.getById(id);
    }

    public PaginationDto<DormDto> search(SearchDto searchDto) {
        return storageService.search(searchDto);
    }

    public void update(DormDto dto) {
        storageService.update(dto);
    }

    public List<DormBriefDto> getBriefAll() {
        return storageService.getBriefAll();
    }
}
