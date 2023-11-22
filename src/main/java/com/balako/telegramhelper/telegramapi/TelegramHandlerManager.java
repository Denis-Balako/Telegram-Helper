package com.balako.telegramhelper.telegramapi;

import com.balako.telegramhelper.exception.TelegramBotException;
import com.balako.telegramhelper.telegramapi.handlers.TelegramUpdateHandler;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class TelegramHandlerManager {
    private final List<TelegramUpdateHandler> handlers;

    public SendMessage handleUpdate(Update update) {
        Message message = update.getMessage();
        return handlers.stream()
                .filter(handler -> handler.isSupports(message.getText()))
                .map(handler -> handler.handle(message))
                .findFirst()
                .orElseGet(() -> handlers.stream()
                        .findFirst()
                        .orElseThrow(() -> new TelegramBotException(
                                "Supporting handler not found.")
                        )
                        .getChatGptAnswer(message));
    }
}
