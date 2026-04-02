package com.restocore.restocore_api.mapper;

import com.restocore.restocore_api.dtos.CreateUserRequestDTO;
import com.restocore.restocore_api.dtos.CreateUserResponseDTO;
import com.restocore.restocore_api.entity.Address;
import com.restocore.restocore_api.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public User toEntity(CreateUserRequestDTO dto) {
        Address address = new Address();
        address.setStreet(dto.street());
        address.setNumber(dto.number());
        address.setCity(dto.city());
        address.setZipCode(dto.zipCode());

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setLogin(dto.login());
        user.setPassword(dto.password());
        user.setUserType(dto.userType());
        user.setAddress(address);

        return user;
    }

    public CreateUserResponseDTO toResponse(User user) {
        return new CreateUserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserType()
        );
    }
}
