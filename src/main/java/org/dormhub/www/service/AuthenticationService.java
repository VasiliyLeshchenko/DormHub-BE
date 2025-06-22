package org.dormhub.www.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rq.SignInRequest;
import org.dormhub.www.controller.rest.dto.rs.JwtAuthenticationResponse;
import org.dormhub.www.service.dto.RoleDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final JwtKeyProvider jwtKeyProvider;
    private final PasswordEncryptionService encryptionService;

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        log.debug("Sign in user with login: {}", request.getLogin());
        UserDetails user = userService.getUserDetails(request.getLogin());

        String decrypt = encryptionService.decrypt(user.getPassword());

        if (decrypt.equals(request.getPassword())) {
            return new JwtAuthenticationResponse(jwtService.generateToken(jwtKeyProvider.getPrivateKey(), user));
        } else {
            log.error("Incorrect password");
            throw new IllegalArgumentException("Неверный пароль");
        }
    }

    public List<RoleDto> getUserRoles(UUID userId) {
        return userService.getById(userId).getRoles();
    }

}
