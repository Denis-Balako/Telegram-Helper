package com.balako.telegramhelper.repository;

import com.balako.telegramhelper.model.TelegramMessage;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TelegramMessageRepository extends JpaRepository<TelegramMessage, Long> {
    @Query("FROM TelegramMessage tm "
            + "LEFT JOIN FETCH tm.chat "
            + "LEFT JOIN FETCH tm.telegramUser")
    Page<TelegramMessage> findAllWithChatAndUser(Pageable pageable);

    @Query("FROM TelegramMessage tm "
            + "LEFT JOIN FETCH tm.chat "
            + "LEFT JOIN FETCH tm.telegramUser "
            + "WHERE tm.messageId = :id")
    Optional<TelegramMessage> findByMessageIdWithChatAndUser(Long id);

    @Query("FROM TelegramMessage tm "
            + "LEFT JOIN FETCH tm.chat "
            + "LEFT JOIN FETCH tm.telegramUser "
            + "WHERE tm.telegramUser.userId = :userId")
    Page<TelegramMessage> findByUserIdWithPage(Long userId, Pageable pageable);

    @Query("FROM TelegramMessage tm "
            + "LEFT JOIN FETCH tm.chat "
            + "LEFT JOIN FETCH tm.telegramUser "
            + "WHERE tm.chat.chatId = :chatId")
    Page<TelegramMessage> findByChatIdWithPage(Long chatId, Pageable pageable);
}
