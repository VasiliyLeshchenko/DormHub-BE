package org.dormhub.www.controller.rest.dto.rs.tenant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dormhub.www.controller.rest.dto.rs.apartment.ApartmentBriefResponse;
import org.dormhub.www.controller.rest.dto.rs.dorm.DormBriefResponse;
import org.dormhub.www.controller.rest.dto.rs.user.UserInfoResponse;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TenantInfoResponse extends UserInfoResponse {

    private DormBriefResponse dorm;

    private String faculty;

    private Integer course;

    private String group;

    private ApartmentBriefResponse apartment;

}
