package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CredentialRequest(
       UUID id,

       @NotBlank(message = "Отсутстует логин")
       String login,

       @NotBlank(message = "Отсутстует пароль")
       String password
) { }
