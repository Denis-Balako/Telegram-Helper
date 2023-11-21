package com.balako.telegramhelper.mapper;

import com.balako.telegramhelper.dto.user.request.UserRegistrationRequestDto;
import com.balako.telegramhelper.dto.user.response.UserRegistrationResponseDto;
import com.balako.telegramhelper.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserRegistrationResponseDto toUserResponse(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
