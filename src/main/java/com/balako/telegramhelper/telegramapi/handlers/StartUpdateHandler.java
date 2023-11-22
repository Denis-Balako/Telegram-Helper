package com.balako.telegramhelper.telegramapi.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class StartUpdateHandler extends TelegramUpdateHandler {
    private static final String START_COMMAND = "/start";
    private static final String START_TEXT = "Hello, %s! My name is %s and "
            + "I give you advice on almost everything in the world";

    @Override
    public SendMessage handle(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text(START_TEXT)
                .build();
    }

    @Override
    public boolean isSupports(String command) {
        return command.equals(START_COMMAND);
    }
}
