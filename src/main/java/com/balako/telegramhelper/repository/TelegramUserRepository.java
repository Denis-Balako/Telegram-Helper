package com.balako.telegramhelper.repository;

import com.balako.telegramhelper.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
}
