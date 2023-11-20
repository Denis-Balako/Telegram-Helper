package com.balako.telegramhelper.dto.telegram.response;

import com.balako.telegramhelper.model.TelegramMessage;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TelegramMessageDto {
    private Long messageId;
    private LocalDateTime date;
    private TelegramChatDto chat;
    private TelegramUserDto telegramUser;
    private String text;
    private TelegramMessage.MessageType type;
}
