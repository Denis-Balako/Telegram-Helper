package com.balako.telegramhelper.telegramapi.handlers;

import com.balako.telegramhelper.dto.chatgpt.response.ChatResponseDto;
import com.balako.telegramhelper.exception.ChatGptException;
import com.balako.telegramhelper.service.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public abstract class TelegramUpdateHandler implements UpdateHandler {
    private static final int RESPONSE_INDEX = 0;
    private ChatGptService chatGptService;

    @Autowired
    public void setChatGptService(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    public SendMessage getChatGptAnswer(Message message) {
        String responseFromChatGpt;
        try {
            responseFromChatGpt = getResponseFromChatGpt(message.getText());
        } catch (RestClientResponseException e) {
            throw new ChatGptException("Can't get response from ChatGPT API: " + e.getMessage(), e);
        }
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text(responseFromChatGpt)
                .build();
    }

    private String getResponseFromChatGpt(String prompt) {
        ChatResponseDto responseDto = chatGptService.getChatGptResponse(prompt);
        return responseDto.getChoices().get(RESPONSE_INDEX).getMessage().getContent();
    }
}
