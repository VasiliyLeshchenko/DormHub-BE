package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import org.dormhub.www.service.dto.CredentialDto;
import org.dormhub.www.service.dto.PaginationDto;
import org.dormhub.www.service.dto.SearchDto;
import org.dormhub.www.service.dto.TenantInfoDto;
import org.dormhub.www.service.dto.apartment.ApartmentDto;
import org.dormhub.www.service.dto.tenant.TenantDto;
import org.dormhub.www.storage.entity.enums.ApartmentType;
import org.dormhub.www.storage.service.TenantStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantStorageService storageService;
    private final PasswordEncryptionService encryptionService;
    private final ApartmentService apartmentService;

    @Transactional
    public UUID save(TenantInfoDto dto) {
        encryptPassword(dto.getCredential());
        validate(dto);
        TenantDto savedDto = storageService.save(dto);
        return savedDto.getId();
    }

    @Transactional
    public void delete(UUID id) {
        storageService.delete(id);
    }

    @Transactional
    public void update(TenantInfoDto dto) {
        encryptPassword(dto.getCredential());
        validate(dto);
        storageService.update(dto);
    }

    public TenantDto getById(UUID id) {
        return storageService.getById(id);
    }

    public TenantInfoDto getInfoById(UUID id) {
        TenantInfoDto dto = storageService.getInfoById(id);
        decryptPassword(dto.getCredential());
        return dto;
    }

    public PaginationDto<TenantDto> search(UUID dormId, UUID apartmentId, SearchDto searchDto) {
        return storageService.search(dormId, apartmentId, searchDto);
    }

    private void encryptPassword(CredentialDto credential) {
        credential.setPassword(encryptionService.encrypt(credential.getPassword()));
    }

    private void decryptPassword(CredentialDto credential) {
        credential.setPassword(encryptionService.decrypt(credential.getPassword()));
    }

    private void validate(TenantInfoDto tenant) {
        if (tenant.getApartment() != null) {
            apartmentService.validateRoomForCheckIn(tenant.getApartment().id(), tenant.getDorm().id());
        }
    }

}
