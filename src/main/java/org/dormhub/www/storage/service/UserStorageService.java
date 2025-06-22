package org.dormhub.www.storage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.service.dto.user.UserDto;
import org.dormhub.www.storage.mapper.UserStorageMapper;
import org.dormhub.www.storage.repository.UserRepository;
import org.dormhub.www.exception.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserStorageService {

    private static final String NOT_FOUND_BY_LOGIN = "Пользотель с логином '%s' не найден";
    private static final String NOT_FOUND_BY_ID = "Пользователь с ID (%s) не найден";
    private final UserRepository repository;
    private final UserStorageMapper mapper;

    public UserDetails getUserDetails(String login) {
        return repository.findByCredentialLogin(login)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_BY_LOGIN.formatted(login)));
    }

    public UserDto getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_BY_ID.formatted(id)));
    }

}
