package com.restocore.restocore_api.mapper;

import com.restocore.restocore_api.dtos.CreateUserRequestDTO;
import com.restocore.restocore_api.dtos.CreateUserResponseDTO;
import com.restocore.restocore_api.dtos.GetAllUserResponseDTO;
import com.restocore.restocore_api.entity.Address;
import com.restocore.restocore_api.entity.User;
import com.restocore.restocore_api.factory.UserFactory;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    private UserFactory userFactory;

    @Autowired
    public void setUserFactory(UserFactory userFactory) {
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

    public abstract CreateUserResponseDTO toCreateUserResponseDTO(User user);

    public abstract GetAllUserResponseDTO toGetAllUserResponseDTO(User user);

    public abstract List<GetAllUserResponseDTO> toGetAllUserResponseDTO(List<User> users);
}
