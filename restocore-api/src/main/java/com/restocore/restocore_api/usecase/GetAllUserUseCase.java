package com.restocore.restocore_api.usecase;

import com.restocore.restocore_api.dtos.GetAllUserResponseDTO;
import com.restocore.restocore_api.mapper.UserMapper;
import com.restocore.restocore_api.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public GetAllUserUseCase(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<GetAllUserResponseDTO> execute() {
        var users = userRepository.findAll();
        return userMapper.toGetAllResponseList(users);
    }
}
