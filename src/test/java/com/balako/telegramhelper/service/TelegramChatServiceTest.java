package com.balako.telegramhelper.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.balako.telegramhelper.dto.telegram.response.TelegramChatDto;
import com.balako.telegramhelper.model.TelegramChat;
import com.balako.telegramhelper.repository.TelegramChatRepository;
import com.balako.telegramhelper.service.impl.TelegramChatServiceImpl;
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
public class TelegramChatServiceTest {
    @InjectMocks
    private TelegramChatServiceImpl telegramChatService;
    @Mock
    private TelegramChatRepository telegramChatRepository;

    @Test
    @DisplayName("Get telegram chat list by valid request")
    public void findAll_validRequest_returnTelegramChatDtoList() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TelegramChat> telegramChats = new PageImpl<>(List.of(createTelegramChat()));
        List<TelegramChatDto> expected = List.of(createTelegramChatDto());

        when(telegramChatRepository.findAll(eq(pageable))).thenReturn(telegramChats);

        List<TelegramChatDto> actual = telegramChatService.findAll(pageable);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get telegram chat by valid ID")
    public void findById_validId_returnTelegramChatDto() {
        TelegramChat validChat = createTelegramChat();
        TelegramChatDto expected = createTelegramChatDto();

        when(telegramChatRepository.findById(anyLong())).thenReturn(Optional.of(validChat));

        TelegramChatDto actual = telegramChatService.findById(validChat.getChatId());

        assertEquals(expected, actual);
    }

    private TelegramChat createTelegramChat() {
        TelegramChat chat = new TelegramChat();
        chat.setTitle("Title");
        chat.setChatType(TelegramChat.ChatType.PRIVATE);
        chat.setChatId(100_000_000L);
        return chat;
    }

    private TelegramChatDto createTelegramChatDto() {
        TelegramChat chat = createTelegramChat();
        return new TelegramChatDto(chat.getChatId(),
                chat.getChatType(),
                chat.getTitle());
    }
}
