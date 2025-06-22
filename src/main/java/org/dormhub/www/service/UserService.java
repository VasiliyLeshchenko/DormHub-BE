package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import org.dormhub.www.service.dto.user.UserDto;
import org.dormhub.www.storage.service.UserStorageService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserStorageService storageService;

    public UserDetails getUserDetails(String login) {
        return storageService.getUserDetails(login);
    }

    public UserDto getById(UUID id) {
        return storageService.getById(id);
    }
}
