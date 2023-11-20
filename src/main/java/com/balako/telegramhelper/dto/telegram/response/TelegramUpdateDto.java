package com.balako.telegramhelper.dto.telegram.response;

import com.balako.telegramhelper.model.TelegramChat;
import com.balako.telegramhelper.model.TelegramMessage;
import com.balako.telegramhelper.model.TelegramUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TelegramUpdateDto {
    private Integer messageId;
    private Integer date;
    private TelegramUser user;
    private TelegramChat chat;
    private String text;
    private TelegramMessage.MessageType type;
}
