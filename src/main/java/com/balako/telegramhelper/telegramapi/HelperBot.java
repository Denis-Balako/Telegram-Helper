package com.balako.telegramhelper.telegramapi;

import com.balako.telegramhelper.dto.chatgpt.response.ChatResponseDto;
import com.balako.telegramhelper.exception.ChatGptException;
import com.balako.telegramhelper.model.TelegramMessage;
import com.balako.telegramhelper.service.ChatGptService;
import com.balako.telegramhelper.service.TelegramMessageService;
import org.springframework.web.client.RestClientResponseException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelperBot extends TelegramLongPollingBot {
    private static final int RESPONSE_INDEX = 0;
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
        telegramMessageService.save(update, TelegramMessage.MessageType.RECEIVED);
        try {
            String responseFromChatGpt = getResponseFromChatGpt(update.getMessage().getText());
            sendMessage(responseFromChatGpt, update.getMessage().getChatId());
            //TODO: add sent message to DB
        } catch (RestClientResponseException e) {
            sendMessage(e.getMessage(), update.getMessage().getChatId());
            throw new ChatGptException("Can't get response from ChatGPT API: " + e.getMessage(), e);
        }
    }

    private String getResponseFromChatGpt(String prompt) {
        ChatResponseDto responseDto = chatGptService.getChatGptResponse(prompt);
        return responseDto.getChoices().get(RESPONSE_INDEX).getMessage().getContent();
    }

    private void sendMessage(String text, Long chatId) {
        try {
            execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
