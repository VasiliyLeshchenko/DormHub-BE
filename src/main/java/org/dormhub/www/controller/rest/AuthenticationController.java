package org.dormhub.www.controller.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rq.SignInRequest;
import org.dormhub.www.controller.rest.dto.rs.JwtAuthenticationResponse;
import org.dormhub.www.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.dormhub.www.util.UrlConstants.AUTH;
import static org.dormhub.www.util.UrlConstants.BASE_API_V1;
import static org.dormhub.www.util.UrlConstants.SIGN_IN;

@Slf4j
@RestController
@RequestMapping(BASE_API_V1 + AUTH)
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(SIGN_IN)
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        log.info("Sign in request with login: {}", request.getLogin());
        return authenticationService.signIn(request);
    }

}
