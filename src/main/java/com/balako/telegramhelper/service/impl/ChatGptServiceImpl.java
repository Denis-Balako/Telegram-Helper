package com.balako.telegramhelper.service.impl;

import com.balako.telegramhelper.dto.chatgpt.request.ChatRequestDto;
import com.balako.telegramhelper.dto.chatgpt.response.ChatResponseDto;
import com.balako.telegramhelper.exception.ChatGptException;
import com.balako.telegramhelper.service.ChatGptService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGptServiceImpl implements ChatGptService {
    private final RestTemplate restTemplate;
    @Value("${openai.model}")
    private String model;
    @Value("${openai.api.url}")
    private String apiUrl;

    public ChatGptServiceImpl(@Qualifier("openaiRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ChatResponseDto getChatGptResponse(String prompt) {
        ChatRequestDto request = new ChatRequestDto(model, prompt);
        ChatResponseDto response = restTemplate.postForObject(
                apiUrl, request, ChatResponseDto.class);
        if (response == null
                || response.getChoices() == null
                || response.getChoices().isEmpty()) {
            throw new ChatGptException("No response", new RuntimeException());
        }
        return response;
    }
}
