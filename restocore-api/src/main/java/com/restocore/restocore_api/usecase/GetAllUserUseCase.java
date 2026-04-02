package com.restocore.restocore_api.usecase;

import com.restocore.restocore_api.entity.User;
import com.restocore.restocore_api.mapper.UserMapper;
import com.restocore.restocore_api.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUserUseCase {

    private final UserRepository userRepository;

    public GetAllUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> execute() {
        return userRepository.findAll();
    }
}
