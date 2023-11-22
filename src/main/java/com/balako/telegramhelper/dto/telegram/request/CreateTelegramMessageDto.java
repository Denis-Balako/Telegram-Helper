package com.balako.telegramhelper.dto.telegram.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateTelegramMessageDto {
    @Length(max = 4096)
    @NotBlank
    private String text;
    @NotNull
    private Long chatId;
}
