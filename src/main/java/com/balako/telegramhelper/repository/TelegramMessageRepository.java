package com.balako.telegramhelper.repository;

import com.balako.telegramhelper.model.TelegramMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramMessageRepository extends JpaRepository<TelegramMessage, Long> {
    @Query("SELECT tm FROM TelegramMessage tm WHERE tm.telegramUser.userId = :userId")
    Page<TelegramMessage> findByUserIdWithPage(Long userId, Pageable pageable);

    @Query("SELECT tm FROM TelegramMessage tm WHERE tm.chat.chatId = :chatId")
    Page<TelegramMessage> findByChatIdWithPage(Long chatId, Pageable pageable);
}
