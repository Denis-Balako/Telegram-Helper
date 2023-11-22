package com.balako.telegramhelper.config;

import com.balako.telegramhelper.service.TelegramMessageService;
import com.balako.telegramhelper.telegramapi.HelperBot;
import com.balako.telegramhelper.telegramapi.TelegramHandlerManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@ComponentScan(basePackages = "com.balako.telegramhelper")
public class TelegramBotConfig {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String token;
    private final TelegramHandlerManager handlerManager;
    private final TelegramMessageService telegramMessageService;

    public TelegramBotConfig(TelegramHandlerManager handlerManager,
                             TelegramMessageService telegramMessageService) {
        this.handlerManager = handlerManager;
        this.telegramMessageService = telegramMessageService;
    }

    @Bean
    public HelperBot createBot() {
        return new HelperBot(botName,
                token,
                handlerManager,
                telegramMessageService);
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(createBot());
    }
}
