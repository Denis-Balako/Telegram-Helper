package com.balako.telegramhelper.controller;

import com.balako.telegramhelper.dto.telegram.request.CreateTelegramMessageDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.service.TelegramMessageService;
import com.balako.telegramhelper.telegramapi.HelperBot;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/messages")
public class TelegramMessageController {
    private final TelegramMessageService telegramMessageService;
    private final HelperBot telegramBot;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<TelegramMessageDto> getAllMessages(Pageable pageable) {
        return telegramMessageService.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public TelegramMessageDto getAllMessagesById(@PathVariable Long id) {
        return telegramMessageService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public TelegramMessageDto sendMessage(@RequestBody @Valid CreateTelegramMessageDto messageDto) {
        return telegramBot.sendMessage(messageDto);
    }
}
