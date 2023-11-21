package com.balako.telegramhelper.repository;

import com.balako.telegramhelper.model.TelegramChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramChatRepository extends JpaRepository<TelegramChat, Long> {
}
