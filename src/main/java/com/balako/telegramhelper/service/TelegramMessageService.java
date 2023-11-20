package com.balako.telegramhelper.service;

import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramUpdateDto;

public interface TelegramMessageService {
    TelegramMessageDto save(TelegramUpdateDto update);
}
