package com.balako.telegramhelper.service;

import com.balako.telegramhelper.dto.user.request.UserRegistrationRequestDto;
import com.balako.telegramhelper.dto.user.response.UserRegistrationResponseDto;
import com.balako.telegramhelper.exception.RegistrationException;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException;
}
