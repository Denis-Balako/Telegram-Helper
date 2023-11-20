package com.balako.telegramhelper.controller;

import com.balako.telegramhelper.dto.telegram.response.TelegramUserDto;
import com.balako.telegramhelper.service.TelegramUserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/logs")
public class AdminController {
    private final TelegramUserService telegramUserService;

    @GetMapping("/users")
    public List<TelegramUserDto> getAllUsers(Pageable pageable) {
        return telegramUserService.findAll(pageable);
    }

    @GetMapping("/users/{id}")
    public TelegramUserDto getById(@PathVariable Long id) {
        return telegramUserService.getById(id);
    }
}
