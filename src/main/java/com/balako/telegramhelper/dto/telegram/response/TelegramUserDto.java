package com.balako.telegramhelper.dto.telegram.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TelegramUserDto {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
}
