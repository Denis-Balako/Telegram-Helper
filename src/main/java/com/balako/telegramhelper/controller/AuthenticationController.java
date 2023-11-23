package com.balako.telegramhelper.controller;

import com.balako.telegramhelper.dto.user.request.UserLoginRequestDto;
import com.balako.telegramhelper.dto.user.request.UserRegistrationRequestDto;
import com.balako.telegramhelper.dto.user.response.UserLoginResponseDto;
import com.balako.telegramhelper.dto.user.response.UserRegistrationResponseDto;
import com.balako.telegramhelper.exception.RegistrationException;
import com.balako.telegramhelper.service.AuthenticationService;
import com.balako.telegramhelper.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication",
        description = "Endpoints for registration and login")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Login the user")
    @PostMapping("/login")
    public UserLoginResponseDto login(
            @RequestBody @Valid UserLoginRequestDto requestDto
    ) {
        return authenticationService.authenticate(requestDto);
    }

    @Operation(summary = "Register the user")
    @PostMapping("/register")
    @ResponseBody
    public UserRegistrationResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto requestDto
    )
            throws RegistrationException {
        return userService.register(requestDto);
    }
}
