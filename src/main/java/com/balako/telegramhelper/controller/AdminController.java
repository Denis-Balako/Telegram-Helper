package com.balako.telegramhelper.controller;

import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramUserDto;
import com.balako.telegramhelper.service.TelegramMessageService;
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
    private final TelegramMessageService telegramMessageService;

    @GetMapping
    public List<TelegramMessageDto> getAllMessages(Pageable pageable) {
        return telegramMessageService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public TelegramMessageDto getAllMessagesById(@PathVariable Long id) {
        return telegramMessageService.findById(id);
    }

    @GetMapping("/users")
    public List<TelegramUserDto> getAllUsers(Pageable pageable) {
        return telegramUserService.findAll(pageable);
    }

    @GetMapping("/users/{id}")
    public TelegramUserDto getUserById(@PathVariable Long id) {
        return telegramUserService.getById(id);
    }

    @GetMapping("/users/{id}/messages")
    public List<TelegramMessageDto> getAllMessagesByUserId(
            @PathVariable Long id, Pageable pageable) {
        return telegramMessageService.findAllByUserId(id, pageable);
    }

    @GetMapping("/chats/{id}/messages")
    public List<TelegramMessageDto> getAllMessagesByChatId(
            @PathVariable Long id, Pageable pageable) {
        return telegramMessageService.findAllByChatId(id, pageable);
    }
}
