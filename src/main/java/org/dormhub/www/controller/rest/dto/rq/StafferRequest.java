package org.dormhub.www.controller.rest.dto.rq;

import jakarta.validation.constraints.NotBlank;
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
public class StafferRequest extends UserRequest {

    @NotBlank(message = "Не указана должность")
    private String position;

    private UUID dormId;

}
