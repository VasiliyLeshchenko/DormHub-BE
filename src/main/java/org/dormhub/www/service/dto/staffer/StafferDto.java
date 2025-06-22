package org.dormhub.www.service.dto.staffer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dormhub.www.controller.rest.dto.rs.dorm.DormBriefResponse;
import org.dormhub.www.service.dto.user.UserDto;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StafferDto extends UserDto {

    private String position;

    private DormBriefResponse dorm;

}