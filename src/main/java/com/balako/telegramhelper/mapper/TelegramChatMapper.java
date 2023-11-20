package com.balako.telegramhelper.mapper;

import com.balako.telegramhelper.dto.telegram.response.TelegramChatDto;
import com.balako.telegramhelper.model.TelegramChat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TelegramChatMapper {
    TelegramChatMapper INSTANCE = Mappers.getMapper(TelegramChatMapper.class);

    TelegramChatDto toDto(TelegramChat chat);
}
