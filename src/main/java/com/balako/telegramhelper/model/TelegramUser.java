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
@Table(name = "telegram_user")
@SQLDelete(sql = "UPDATE telegram_user SET is_deleted = true WHERE user_id = ?")
@Where(clause = "is_deleted = false")
public class TelegramUser {
    @Id
    @Column(nullable = false, unique = true)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private boolean isDeleted = false;
}
