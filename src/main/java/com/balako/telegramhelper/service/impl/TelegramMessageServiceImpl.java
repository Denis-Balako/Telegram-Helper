package com.balako.telegramhelper.service.impl;

import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@AllArgsConstructor
@Service
public class TelegramMessageServiceImpl implements TelegramMessageService {
    private TelegramMessageRepository telegramMessageRepository;
    private TelegramUserRepository telegramUserRepository;
    private TelegramChatRepository telegramChatRepository;

    @Override
    public TelegramMessageDto save(Update update, TelegramMessage.MessageType messageType) {
        TelegramMessage message = new TelegramMessage();
        message.setMessageId(Long.valueOf(update.getMessage().getMessageId()));
        message.setDate(
                convertUnixTimeToLocalDateTime(Long.valueOf(update.getMessage().getDate()))
        );
        message.setTelegramUser(findOrCreateTelegramUser(update.getMessage().getFrom()));
        message.setChat(findOrCreateTelegramChat(update.getMessage().getChat()));
        message.setText(update.getMessage().getText());
        message.setType(messageType);
        return TelegramMessageMapper.INSTANCE.toDto(telegramMessageRepository.save(message));
    }

    private LocalDateTime convertUnixTimeToLocalDateTime(Long epochTimeMillis) {
        Instant instant = Instant.ofEpochMilli(epochTimeMillis);
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    private TelegramUser findOrCreateTelegramUser(User from) {
        return telegramUserRepository.findById(from.getId())
                .orElseGet(() -> {
                    TelegramUser user = new TelegramUser();
                    user.setUserId(from.getId());
                    user.setFirstName(from.getFirstName());
                    user.setLastName(from.getLastName() != null ? from.getLastName() : "");
                    return telegramUserRepository.save(user);
                });
    }

    private TelegramChat findOrCreateTelegramChat(Chat telegramChat) {
        return telegramChatRepository.findById(telegramChat.getId())
                .orElseGet(() -> {
                    TelegramChat chat = new TelegramChat();
                    chat.setChatId(telegramChat.getId());
                    chat.setChatType(TelegramChat.ChatType.valueOf(
                            telegramChat.getType().toUpperCase()));
                    chat.setTitle(telegramChat.getTitle() != null ? telegramChat.getTitle() : "");
                    return telegramChatRepository.save(chat);
                });
    }
}
