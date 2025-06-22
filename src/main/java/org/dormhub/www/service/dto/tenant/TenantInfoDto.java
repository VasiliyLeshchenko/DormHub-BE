package org.dormhub.www.service.dto.tenant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dormhub.www.service.dto.apartment.ApartmentBriefDto;
import org.dormhub.www.service.dto.dorm.DormBriefDto;
import org.dormhub.www.service.dto.user.UserInfoDto;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TenantInfoDto extends UserInfoDto {

    private DormBriefDto dorm;

    private String faculty;

    private Integer course;

    private String group;

    private ApartmentBriefDto apartment;

}
