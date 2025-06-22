package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rs.user.UserBriefResponse;
import org.dormhub.www.service.dto.user.UserBriefDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserWebMapper extends CommonWebMapper {

    List<UserBriefResponse> toResponse(List<UserBriefDto> dtos);

}
