package com.balako.telegramhelper.service.impl;

import com.balako.telegramhelper.dto.telegram.response.TelegramUserDto;
import com.balako.telegramhelper.mapper.TelegramUserMapper;
import com.balako.telegramhelper.model.TelegramUser;
import com.balako.telegramhelper.repository.TelegramUserRepository;
import com.balako.telegramhelper.service.TelegramUserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;
    private final TelegramUserMapper telegramUserMapper = TelegramUserMapper.INSTANCE;

    @Override
    public List<TelegramUserDto> findAll(Pageable pageable) {
        return telegramUserRepository.findAll(pageable).stream()
                .map(telegramUserMapper::toDto)
                .toList();
    }

    @Override
    public TelegramUserDto getById(Long id) {
        TelegramUser user = telegramUserRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find user by id: " + id)
        );
        return telegramUserMapper.toDto(user);
    }
}
