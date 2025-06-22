package org.dormhub.www.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dormhub.www.service.dto.apartment.ApartmentBriefDto;
import org.dormhub.www.service.dto.dorm.DormDto;
import org.dormhub.www.service.dto.user.UserInfoDto;
import org.dormhub.www.storage.entity.enums.Faculty;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TenantInfoDto extends UserInfoDto {

    private DormDto dorm;

    private Faculty faculty;

    private Integer course;

    private String group;

    private ApartmentBriefDto apartment;

}
