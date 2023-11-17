package com.balako.telegramhelper.service;

import com.balako.telegramhelper.dto.chatgpt.response.ChatResponseDto;

public interface ChatGptService {
    ChatResponseDto getChatGptResponse(String prompt);
}
