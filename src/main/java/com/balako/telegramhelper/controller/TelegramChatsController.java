package com.balako.telegramhelper.controller;

import com.balako.telegramhelper.dto.telegram.response.TelegramChatDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.service.TelegramChatService;
import com.balako.telegramhelper.service.TelegramMessageService;
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
@RequestMapping("/chats")
public class TelegramChatsController {
    private final TelegramMessageService telegramMessageService;
    private final TelegramChatService telegramChatService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public List<TelegramChatDto> findAll(Pageable pageable) {
        return telegramChatService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public TelegramChatDto findById(@PathVariable Long id) {
        return telegramChatService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/messages")
    public List<TelegramMessageDto> getAllMessagesByChatId(
            @PathVariable Long id, Pageable pageable) {
        return telegramMessageService.findAllByChatId(id, pageable);
    }
}
