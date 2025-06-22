package org.dormhub.www.storage.mapper;

import org.dormhub.www.service.dto.user.UserBriefDto;
import org.dormhub.www.service.dto.user.UserDto;
import org.dormhub.www.service.dto.user.UserInfoDto;
import org.dormhub.www.storage.entity.UserEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserStorageMapper {

    UserBriefDto toBriefDto(UserEntity entity);

    UserInfoDto toInfoDto(UserEntity entity);

    UserDto toDto(UserEntity entity);
}
