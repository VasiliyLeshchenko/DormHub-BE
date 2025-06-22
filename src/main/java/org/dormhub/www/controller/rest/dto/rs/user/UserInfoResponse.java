package org.dormhub.www.controller.rest.dto.rs.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dormhub.www.controller.rest.dto.rs.RoleResponse;
import org.dormhub.www.controller.rest.dto.rs.CredentialResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String fatherName;

    private Character sex;

    private LocalDate birthdate;

    private String phone;

    private String email;

    private CredentialResponse credential;

    private List<RoleResponse> roles;

}
