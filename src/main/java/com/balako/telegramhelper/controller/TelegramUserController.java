package com.balako.telegramhelper.controller;

import com.balako.telegramhelper.dto.telegram.response.TelegramMessageDto;
import com.balako.telegramhelper.dto.telegram.response.TelegramUserDto;
import com.balako.telegramhelper.service.TelegramMessageService;
import com.balako.telegramhelper.service.TelegramUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Telegram users management",
        description = "Endpoints for managing telegram users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class TelegramUserController {
    private final TelegramUserService telegramUserService;
    private final TelegramMessageService telegramMessageService;

    @Operation(summary = "Get all telegram users",
            description = "Pagination and sorting included.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<TelegramUserDto> getAllUsers(Pageable pageable) {
        return telegramUserService.findAll(pageable);
    }

    @Operation(summary = "Get telegram user by ID")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public TelegramUserDto getUserById(@PathVariable Long id) {
        return telegramUserService.getById(id);
    }

    @Operation(summary = "Get all messages by user ID",
            description = "Pagination and sorting included.")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}/messages")
    public List<TelegramMessageDto> getAllMessagesByUserId(
            @PathVariable Long id, Pageable pageable) {
        return telegramMessageService.findAllByUserId(id, pageable);
    }
}
