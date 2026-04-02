package com.restocore.restocore_api.usecase;

import com.restocore.restocore_api.dtos.CreateUserRequestDTO;
import com.restocore.restocore_api.dtos.CreateUserResponseDTO;
import com.restocore.restocore_api.mapper.UserMapper;
import com.restocore.restocore_api.repository.UserRepository;
import com.restocore.restocore_api.shared.TextNormalizer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TextNormalizer textNormalizer;

    public CreateUserUseCase(UserRepository userRepository, UserMapper userMapper, TextNormalizer textNormalizer) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.textNormalizer = textNormalizer;
    }

    @Transactional
    public CreateUserResponseDTO execute(CreateUserRequestDTO request) {
        String normalizedEmail = textNormalizer.normalizeToLowerTrim(request.email());
        String normalizedLogin = textNormalizer.normalizeToLowerTrim(request.login());

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new RuntimeException("Email already registered");
        }

        if (userRepository.existsByLogin(normalizedLogin)) {
            throw new RuntimeException("Login already registered");
        }

        var userEntity = userMapper.toEntity(request);
        var savedUser = userRepository.save(userEntity);
        return userMapper.toCreateResponse(savedUser);
    }

}
