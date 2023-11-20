package com.balako.telegramhelper.telegramapi;

import com.balako.telegramhelper.dto.chatgpt.response.ChatResponseDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramUpdateDto;
import com.balako.telegramhelper.exception.ChatGptException;
import com.balako.telegramhelper.model.TelegramChat;
import com.balako.telegramhelper.model.TelegramMessage;
import com.balako.telegramhelper.model.TelegramUser;
import com.balako.telegramhelper.service.ChatGptService;
import com.balako.telegramhelper.service.TelegramMessageService;
import org.springframework.web.client.RestClientResponseException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelperBot extends TelegramLongPollingBot {
    private static final int RESPONSE_INDEX = 0;
    private static final TelegramMessage.MessageType MESSAGE_TYPE_FOR_USER =
            TelegramMessage.MessageType.RECEIVED;
    private static final TelegramMessage.MessageType MESSAGE_TYPE_FOR_BOT =
            TelegramMessage.MessageType.SENT;
    private final String botUsername;
    private final ChatGptService chatGptService;
    private final TelegramMessageService telegramMessageService;

    public HelperBot(String botUsername,
                     String botToken,
                     ChatGptService chatGptService,
                     TelegramMessageService telegramMessageService) {
        super(botToken);
        this.botUsername = botUsername;
        this.chatGptService = chatGptService;
        this.telegramMessageService = telegramMessageService;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String responseFromChatGpt;
        telegramMessageService.save(getUpdateDto(update.getMessage(), MESSAGE_TYPE_FOR_USER));

        try {
            responseFromChatGpt = getResponseFromChatGpt(update.getMessage().getText());
        } catch (RestClientResponseException e) {
            sendMessage(e.getMessage(), update.getMessage().getChatId());
            throw new ChatGptException("Can't get response from ChatGPT API: " + e.getMessage(), e);
        }

        Message sentMessage = sendMessage(responseFromChatGpt, update.getMessage().getChatId());
        telegramMessageService.save(getUpdateDto(sentMessage, MESSAGE_TYPE_FOR_BOT));
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

    private String getResponseFromChatGpt(String prompt) {
        ChatResponseDto responseDto = chatGptService.getChatGptResponse(prompt);
        return responseDto.getChoices().get(RESPONSE_INDEX).getMessage().getContent();
    }

    private Message sendMessage(String text, Long chatId) {
        try {
            return execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
