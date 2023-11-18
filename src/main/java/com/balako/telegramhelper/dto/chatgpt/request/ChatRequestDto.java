package com.balako.telegramhelper.dto.chatgpt.request;

import com.balako.telegramhelper.model.ChatGptMessage;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ChatRequestDto {
    private String model;
    private List<ChatGptMessage> messages;
    //CHECKSTYLE.OFF: MemberName - Naming as a chatGPT object
    private int n;
    //CHECKSTYLE.ON: MemberName
    private double temperature;

    public ChatRequestDto(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new ChatGptMessage("user", prompt));
        this.n = 1;
        this.temperature = 1;
    }
}
