package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import org.dormhub.www.service.dto.RoleDto;
import org.dormhub.www.storage.service.RoleStorageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleStorageService storageService;

    public List<RoleDto> getAll() {
        return storageService.getAll();
    }

    public RoleDto getRoleByName(String name) {
        return storageService.getByName(name);
    }

}
