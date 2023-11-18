package com.balako.telegramhelper.mapper;

import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.model.TelegramMessage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TelegramMessageMapper {
    TelegramMessageMapper INSTANCE = Mappers.getMapper(TelegramMessageMapper.class);

    TelegramMessageDto toDto(TelegramMessage message);
}
