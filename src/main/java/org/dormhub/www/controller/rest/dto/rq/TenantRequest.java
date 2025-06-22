package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TenantRequest extends UserRequest {

    @NotNull(message = "Не указано общежитие")
    private UUID dormId;

    private String faculty;

    private Integer course;

    private String group;

    private UUID apartmentId;

}
