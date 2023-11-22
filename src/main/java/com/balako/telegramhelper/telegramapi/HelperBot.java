package com.balako.telegramhelper.telegramapi;

import com.balako.telegramhelper.dto.telegram.request.CreateTelegramMessageDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramUpdateDto;
import com.balako.telegramhelper.exception.TelegramBotException;
import com.balako.telegramhelper.model.TelegramChat;
import com.balako.telegramhelper.model.TelegramMessage;
import com.balako.telegramhelper.model.TelegramUser;
import com.balako.telegramhelper.service.TelegramMessageService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelperBot extends TelegramLongPollingBot {
    private static final TelegramMessage.MessageType MESSAGE_TYPE_FOR_USER =
            TelegramMessage.MessageType.RECEIVED;
    private static final TelegramMessage.MessageType MESSAGE_TYPE_FOR_BOT =
            TelegramMessage.MessageType.SENT;
    private final String botUsername;
    private final TelegramHandlerManager handlerManager;
    private final TelegramMessageService telegramMessageService;

    public HelperBot(String botUsername,
                     String botToken,
                     TelegramHandlerManager handlerManager,
                     TelegramMessageService telegramMessageService) {
        super(botToken);
        this.botUsername = botUsername;
        this.handlerManager = handlerManager;
        this.telegramMessageService = telegramMessageService;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            telegramMessageService.save(getUpdateDto(update.getMessage(), MESSAGE_TYPE_FOR_USER));
            Message sentMessage = sendMessage(handlerManager.handleUpdate(update));
            telegramMessageService.save(getUpdateDto(sentMessage, MESSAGE_TYPE_FOR_BOT));
        }
    }

    public TelegramMessageDto sendMessage(CreateTelegramMessageDto messageDto) {
        Message sentMessage = sendMessage(SendMessage.builder()
                .chatId(messageDto.getChatId())
                .text(messageDto.getText())
                .build());
        return telegramMessageService.save(getUpdateDto(sentMessage, MESSAGE_TYPE_FOR_BOT));
    }

    private Message sendMessage(SendMessage sendMessage) {
        try {
            return execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new TelegramBotException("Can't send message to chat with id: "
                    + sendMessage.getChatId(),
                    e);
        }
    }

    private TelegramUpdateDto getUpdateDto(Message message,
                                           TelegramMessage.MessageType messageType) {
        TelegramUpdateDto updateDto = new TelegramUpdateDto();
        updateDto.setMessageId(message.getMessageId());
        updateDto.setDate(message.getDate());
        updateDto.setChat(getTelegramChat(message.getChat()));
        updateDto.setType(messageType);
        updateDto.setUser(getTelegramUser(message.getFrom()));
        updateDto.setText(message.getText());
        return updateDto;
    }

    private TelegramChat getTelegramChat(Chat telegramChat) {
        TelegramChat chat = new TelegramChat();
        chat.setChatId(telegramChat.getId());
        chat.setChatType(TelegramChat.ChatType.valueOf(
                telegramChat.getType().toUpperCase()));
        chat.setTitle(telegramChat.getTitle() != null ? telegramChat.getTitle() : "");
        return chat;
    }

    private TelegramUser getTelegramUser(User from) {
        TelegramUser user = new TelegramUser();
        user.setUserId(from.getId());
        user.setUsername(from.getUserName());
        user.setFirstName(from.getFirstName());
        user.setLastName(from.getLastName() != null ? from.getLastName() : "");
        return user;
    }
}
