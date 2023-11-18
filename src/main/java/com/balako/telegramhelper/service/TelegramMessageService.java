package com.balako.telegramhelper.service;

import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.model.TelegramMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramMessageService {
    TelegramMessageDto save(Update update, TelegramMessage.MessageType messageType);
}
