package com.restocore.restocore_api.mapper;

import com.restocore.restocore_api.dtos.CreateUserRequestDTO;
import com.restocore.restocore_api.dtos.CreateUserResponseDTO;
import com.restocore.restocore_api.dtos.GetAllUserResponseDTO;
import com.restocore.restocore_api.entity.Address;
import com.restocore.restocore_api.entity.User;
import com.restocore.restocore_api.factory.UserFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    private final UserFactory userFactory;

    public UserMapper(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    public User toEntity(CreateUserRequestDTO dto) {
        Address address = new Address();
        address.setStreet(dto.street());
        address.setNumber(dto.number());
        address.setCity(dto.city());
        address.setZipCode(dto.zipCode());

        return userFactory.create(
                dto.name(),
                dto.email(),
                dto.login(),
                dto.password(),
                dto.userType(),
                address
        );
    }

    public CreateUserResponseDTO toCreateResponse(User user) {
        return new CreateUserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserType()
        );
    }

    public List<GetAllUserResponseDTO> toGetAllResponseList(List<User> users) {
        return users.stream()
                .map(user -> new GetAllUserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getUserType()
                ))
                .toList();
    }
}
