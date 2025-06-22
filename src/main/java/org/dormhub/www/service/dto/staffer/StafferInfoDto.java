package org.dormhub.www.service.dto.staffer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dormhub.www.service.dto.dorm.DormDto;
import org.dormhub.www.service.dto.user.UserInfoDto;
import org.dormhub.www.storage.entity.enums.StafferPosition;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StafferInfoDto extends UserInfoDto {

    private StafferPosition position;

    private DormDto dorm;

}
