package com.restocore.restocore_api.usecase;

import com.restocore.restocore_api.dtos.CreateUserRequestDTO;
import com.restocore.restocore_api.dtos.CreateUserResponseDTO;
import com.restocore.restocore_api.mapper.UserMapper;
import com.restocore.restocore_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public CreateUserUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public CreateUserResponseDTO execute(CreateUserRequestDTO request) {
        userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    throw new RuntimeException("Email already registered");
                });

        userRepository.findByLogin(request.login())
                .ifPresent(u -> {
                    throw new RuntimeException("Login already registered");
                });

        var userEntity = userMapper.toEntity(request);
        var savedUser = userRepository.save(userEntity);
        return userMapper.toResponse(savedUser);
    }
}
