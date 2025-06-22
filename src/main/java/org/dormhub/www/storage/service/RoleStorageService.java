package org.dormhub.www.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.service.dto.RoleDto;
import org.dormhub.www.storage.entity.RoleEntity;
import org.dormhub.www.storage.mapper.RoleStorageMapper;
import org.dormhub.www.storage.repository.RoleRepository;

import org.dormhub.www.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleStorageService {

    private static final String ROLE_BY_NAME_NOT_FOUND = "Роль с ID (%s) не найдена";
    private final RoleRepository repository;
    private final RoleStorageMapper mapper;

    public List<RoleDto> getAll() {
        List<RoleEntity> roles = repository.findAll();
        return mapper.toDto(roles);
    }

    public RoleDto getByName(String name) {
        return repository.findByName(name)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(ROLE_BY_NAME_NOT_FOUND.formatted(name)));
    }

}
