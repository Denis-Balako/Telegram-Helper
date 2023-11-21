package com.balako.telegramhelper.controller;

import com.balako.telegramhelper.dto.user.request.UserLoginRequestDto;
import com.balako.telegramhelper.dto.user.request.UserRegistrationRequestDto;
import com.balako.telegramhelper.dto.user.response.UserLoginResponseDto;
import com.balako.telegramhelper.dto.user.response.UserRegistrationResponseDto;
import com.balako.telegramhelper.exception.RegistrationException;
import com.balako.telegramhelper.service.AuthenticationService;
import com.balako.telegramhelper.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public UserLoginResponseDto login(
            @RequestBody @Valid UserLoginRequestDto requestDto
    ) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/register")
    @ResponseBody
    public UserRegistrationResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto requestDto
    )
            throws RegistrationException {
        return userService.register(requestDto);
    }
}
