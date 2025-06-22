package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {

    @NotBlank(message = "Отсутстует логин")
    private String login;

    @NotBlank(message = "Отсутствует пароль")
    private String password;
}