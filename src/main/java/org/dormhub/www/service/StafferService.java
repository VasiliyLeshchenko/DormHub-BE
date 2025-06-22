package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import org.dormhub.www.service.dto.CredentialDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.staffer.StafferDto;
import org.dormhub.www.service.dto.staffer.StafferInfoDto;
import org.dormhub.www.storage.service.StafferStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StafferService {

    private final StafferStorageService storageService;
    private final PasswordEncryptionService encryptionService;

    @Transactional
    public UUID save(StafferInfoDto dto) {
        encryptPassword(dto.getCredential());
        StafferInfoDto savedDto = storageService.save(dto);
        return savedDto.getId();
    }

    @Transactional
    public void delete(UUID id) {
        storageService.delete(id);
    }

    @Transactional
    public void update(StafferInfoDto dto) {
        encryptPassword(dto.getCredential());
        storageService.update(dto);
    }

    public StafferDto getById(UUID id) {
        return storageService.getById(id);
    }

    public StafferInfoDto getInfoById(UUID id) {
        StafferInfoDto dto = storageService.getInfoById(id);
        decryptPassword(dto.getCredential());
        return dto;
    }

    public PaginationDto<StafferDto> search(UUID dormId, SearchDto searchDto) {
        return storageService.search(dormId, searchDto);
    }

    private void encryptPassword(CredentialDto credential) {
        credential.setPassword(encryptionService.encrypt(credential.getPassword()));
    }

    private void decryptPassword(CredentialDto credential) {
        credential.setPassword(encryptionService.decrypt(credential.getPassword()));
    }

}
