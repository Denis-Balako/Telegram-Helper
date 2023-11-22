package com.balako.telegramhelper.service;

import com.balako.telegramhelper.dto.telegram.response.TelegramChatDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TelegramChatService {
    List<TelegramChatDto> findAll(Pageable pageable);

    TelegramChatDto findById(Long id);
}
