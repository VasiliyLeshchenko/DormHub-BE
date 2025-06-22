package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private UUID id;

    @NotBlank(message = "Отсутствует имя")
    private String firstName;

    @NotBlank(message = "Отсутствует фамилия")
    private String lastName;

    private String fatherName;

    @NotNull(message = "Отсутствует пол")
    @Pattern(regexp = "[MF]", message = "Недобустимый символ для пола")
    private String sex;

    @NotNull(message = "Отсутстувет дата рождения")
    private LocalDate birthdate;

    @NotBlank(message = "Отсутстувет номер телефона")
    private String phone;

    @Email(message = "Неверный формат email")
    private String email;

    @NotNull
    @Valid
    private CredentialRequest credential;

    @NotEmpty(message = "Отсутстуют роли")
    private List<UUID> roleIds;
}
