package com.balako.telegramhelper.controller;

import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramUserDto;
import com.balako.telegramhelper.service.TelegramMessageService;
import com.balako.telegramhelper.service.TelegramUserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class TelegramUserController {
    private final TelegramUserService telegramUserService;
    private final TelegramMessageService telegramMessageService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<TelegramUserDto> getAllUsers(Pageable pageable) {
        return telegramUserService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public TelegramUserDto getUserById(@PathVariable Long id) {
        return telegramUserService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/messages")
    public List<TelegramMessageDto> getAllMessagesByUserId(
            @PathVariable Long id, Pageable pageable) {
        return telegramMessageService.findAllByUserId(id, pageable);
    }
}
