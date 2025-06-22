package org.dormhub.www.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dormhub.www.service.dto.RoleDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String fatherName;

    private Character sex;

    private LocalDate birthdate;

    private String phone;

    private String email;

    private List<RoleDto> roles;

}
