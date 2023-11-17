package com.balako.telegramhelper.dto.chatgpt.request;

import com.balako.telegramhelper.model.Message;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ChatRequestDto {
    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    public ChatRequestDto(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
        this.n = 1;
        this.temperature = 1;
    }
}
