package com.balako.telegramhelper.telegramapi;

import com.balako.telegramhelper.dto.chatgpt.response.ChatResponseDto;
import com.balako.telegramhelper.service.ChatGptService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelperBot extends TelegramLongPollingBot {
    private static final int RESPONSE_INDEX = 0;
    private final String botUsername;
    private final ChatGptService chatGptService;

    public HelperBot(String botUsername, String botToken, ChatGptService chatGptService) {
        super(botToken);
        this.botUsername = botUsername;
        this.chatGptService = chatGptService;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String responseFromChatGpt = getResponseFromChatGpt(update.getMessage().getText());
        sendMessage(responseFromChatGpt, update.getMessage().getChatId());
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
