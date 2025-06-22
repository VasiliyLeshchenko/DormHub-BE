package org.dormhub.www.controller.rest.dto.rs.staffer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dormhub.www.controller.rest.dto.rs.dorm.DormBriefResponse;
import org.dormhub.www.controller.rest.dto.rs.user.UserResponse;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StafferResponse extends UserResponse {

    private String position;

    private DormBriefResponse dorm;

}