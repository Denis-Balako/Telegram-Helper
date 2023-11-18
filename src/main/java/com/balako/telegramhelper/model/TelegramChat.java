package com.balako.telegramhelper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "telegram_chat")
@SQLDelete(sql = "UPDATE telegram_chat SET is_deleted = true WHERE chat_id = ?")
@Where(clause = "is_deleted = false")
public class TelegramChat {
    @Id
    private Long chatId;
    @Column(nullable = false)
    private ChatType chatType;
    @Column(nullable = false)
    private String title;
    private boolean isDeleted = false;

    public enum ChatType {
        PRIVATE, GROUP, SUPERGROUP, CHANNEL
    }
}
