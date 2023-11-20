package com.balako.telegramhelper.service;

import com.balako.telegramhelper.dto.telegram.response.TelegramUserDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TelegramUserService {
    List<TelegramUserDto> findAll(Pageable pageable);

    TelegramUserDto getById(Long id);
}
