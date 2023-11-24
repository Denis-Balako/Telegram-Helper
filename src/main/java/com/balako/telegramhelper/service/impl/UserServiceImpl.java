package com.balako.telegramhelper.service.impl;

import com.balako.telegramhelper.dto.user.request.UserRegistrationRequestDto;
import com.balako.telegramhelper.dto.user.response.UserRegistrationResponseDto;
import com.balako.telegramhelper.exception.RegistrationException;
import com.balako.telegramhelper.mapper.UserMapper;
import com.balako.telegramhelper.model.Role;
import com.balako.telegramhelper.model.User;
import com.balako.telegramhelper.repository.RoleRepository;
import com.balako.telegramhelper.repository.UserRepository;
import com.balako.telegramhelper.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role = roleRepository.getByName(Role.RoleName.ROLE_ADMIN);
        user.setRoles(Set.of(role));
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}
