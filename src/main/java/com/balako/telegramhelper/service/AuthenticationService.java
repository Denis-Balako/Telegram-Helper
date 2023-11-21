package com.balako.telegramhelper.service;

import com.balako.telegramhelper.dto.user.request.UserLoginRequestDto;
import com.balako.telegramhelper.dto.user.response.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto requestDto);
}
