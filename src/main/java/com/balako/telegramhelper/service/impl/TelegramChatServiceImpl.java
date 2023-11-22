package com.balako.telegramhelper.service.impl;

import com.balako.telegramhelper.dto.telegram.response.TelegramChatDto;
import com.balako.telegramhelper.mapper.TelegramChatMapper;
import com.balako.telegramhelper.model.TelegramChat;
import com.balako.telegramhelper.repository.TelegramChatRepository;
import com.balako.telegramhelper.service.TelegramChatService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramChatServiceImpl implements TelegramChatService {
    private final TelegramChatRepository telegramChatRepository;
    private final TelegramChatMapper telegramChatMapper = TelegramChatMapper.INSTANCE;

    @Override
    public List<TelegramChatDto> findAll(Pageable pageable) {
        return telegramChatRepository.findAll().stream()
                .map(telegramChatMapper::toDto)
                .toList();
    }

    @Override
    public TelegramChatDto findById(Long id) {
        TelegramChat chat = telegramChatRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find chat by id: " + id));
        return telegramChatMapper.toDto(chat);
    }
}
