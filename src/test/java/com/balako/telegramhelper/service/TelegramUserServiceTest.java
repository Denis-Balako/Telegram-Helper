package com.balako.telegramhelper.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.balako.telegramhelper.dto.telegram.response.TelegramUserDto;
import com.balako.telegramhelper.model.TelegramUser;
import com.balako.telegramhelper.repository.TelegramUserRepository;
import com.balako.telegramhelper.service.impl.TelegramUserServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class TelegramUserServiceTest {
    @InjectMocks
    private TelegramUserServiceImpl telegramUserService;
    @Mock
    private TelegramUserRepository telegramUserRepository;

    @Test
    @DisplayName("Get telegram user list by valid request")
    public void findAll_validRequest_returnTelegramUserDtoList() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TelegramUser> users = new PageImpl<>(List.of(createTelegramUser()));
        List<TelegramUserDto> expected = List.of(createTelegramUserDto());

        when(telegramUserRepository.findAll(eq(pageable))).thenReturn(users);

        List<TelegramUserDto> actual = telegramUserService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get telegram user by valid ID")
    void findById_validId_returnTelegramUserDto() {
        TelegramUser validUser = createTelegramUser();
        TelegramUserDto expected = createTelegramUserDto();

        when(telegramUserRepository.findById(anyLong())).thenReturn(Optional.of(validUser));

        TelegramUserDto actual = telegramUserService.getById(validUser.getUserId());

        assertEquals(expected, actual);
    }

    private TelegramUser createTelegramUser() {
        TelegramUser user = new TelegramUser();
        user.setUserId(1L);
        user.setUsername("username");
        user.setFirstName("name");
        user.setLastName("surname");
        return user;
    }

    private TelegramUserDto createTelegramUserDto() {
        TelegramUser user = createTelegramUser();
        return new TelegramUserDto(user.getUserId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName());
    }
}
