package com.balako.telegramhelper.dto.chatgpt.response;

import com.balako.telegramhelper.model.ChatGptMessage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatResponseDto {
    private List<Choice> choices;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Choice {
        private int index;
        private ChatGptMessage message;
    }
}
