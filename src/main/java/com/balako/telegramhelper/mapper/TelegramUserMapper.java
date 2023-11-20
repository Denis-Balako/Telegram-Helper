package com.balako.telegramhelper.mapper;

import com.balako.telegramhelper.dto.telegram.response.TelegramUserDto;
import com.balako.telegramhelper.model.TelegramUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TelegramUserMapper {
    TelegramUserMapper INSTANCE = Mappers.getMapper(TelegramUserMapper.class);

    TelegramUserDto toDto(TelegramUser telegramUser);
}
