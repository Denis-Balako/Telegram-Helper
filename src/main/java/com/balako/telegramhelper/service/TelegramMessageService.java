package com.balako.telegramhelper.service;

import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramUpdateDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TelegramMessageService {
    TelegramMessageDto save(TelegramUpdateDto update);

    TelegramMessageDto findById(Long id);

    List<TelegramMessageDto> findAll(Pageable pageable);

    List<TelegramMessageDto> findAllByUserId(Long userId, Pageable pageable);

    List<TelegramMessageDto> findAllByChatId(Long id, Pageable pageable);
}
