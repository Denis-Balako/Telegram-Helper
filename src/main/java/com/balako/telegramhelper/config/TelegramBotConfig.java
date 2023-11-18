package com.balako.telegramhelper.config;

import com.balako.telegramhelper.service.ChatGptService;
import com.balako.telegramhelper.service.TelegramMessageService;
import com.balako.telegramhelper.telegramapi.HelperBot;
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
    private final ChatGptService chatGptService;
    private final TelegramMessageService telegramMessageService;

    public TelegramBotConfig(ChatGptService chatGptService,
                             TelegramMessageService telegramMessageService) {
        this.chatGptService = chatGptService;
        this.telegramMessageService = telegramMessageService;
    }

    @Bean
    public HelperBot createBot() {
        return new HelperBot(botName, token, chatGptService, telegramMessageService);
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(createBot());
    }
}
