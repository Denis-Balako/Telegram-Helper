package com.balako.telegramhelper.service.impl;

import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramUpdateDto;
import com.balako.telegramhelper.mapper.TelegramMessageMapper;
import com.balako.telegramhelper.model.TelegramChat;
import com.balako.telegramhelper.model.TelegramMessage;
import com.balako.telegramhelper.model.TelegramUser;
import com.balako.telegramhelper.repository.TelegramChatRepository;
import com.balako.telegramhelper.repository.TelegramMessageRepository;
import com.balako.telegramhelper.repository.TelegramUserRepository;
import com.balako.telegramhelper.service.TelegramMessageService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TelegramMessageServiceImpl implements TelegramMessageService {
    private final TelegramMessageRepository telegramMessageRepository;
    private final TelegramUserRepository telegramUserRepository;
    private final TelegramChatRepository telegramChatRepository;
    private final TelegramMessageMapper telegramMessageMapper = TelegramMessageMapper.INSTANCE;

    @Override
    public TelegramMessageDto save(TelegramUpdateDto update) {
        TelegramMessage message = new TelegramMessage();
        message.setMessageId(Long.valueOf(update.getMessageId()));
        message.setDate(
                convertUnixTimeToLocalDateTime(Long.valueOf(update.getDate()))
        );
        message.setChat(findOrCreateTelegramChat(update.getChat()));
        message.setText(update.getText());
        message.setType(update.getType());
        message.setTelegramUser(findOrCreateTelegramUser(update.getUser()));
        return telegramMessageMapper.toDto(telegramMessageRepository.save(message));
    }

    private LocalDateTime convertUnixTimeToLocalDateTime(Long epochTimeMillis) {
        Instant instant = Instant.ofEpochMilli(epochTimeMillis);
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    private TelegramUser findOrCreateTelegramUser(TelegramUser user) {
        return telegramUserRepository.findById(user.getUserId())
                .orElseGet(() -> telegramUserRepository.save(user));
    }

    private TelegramChat findOrCreateTelegramChat(TelegramChat chat) {
        return telegramChatRepository.findById(chat.getChatId())
                .orElseGet(() -> telegramChatRepository.save(chat));
    }
}
