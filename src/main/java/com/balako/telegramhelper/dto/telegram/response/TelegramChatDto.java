package com.balako.telegramhelper.dto.telegram.response;

import com.balako.telegramhelper.model.TelegramChat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TelegramChatDto {
    private Long chatId;
    private TelegramChat.ChatType chatType;
    private String title;
}
