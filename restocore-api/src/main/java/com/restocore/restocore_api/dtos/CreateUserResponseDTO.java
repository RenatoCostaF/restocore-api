package com.restocore.restocore_api.dtos;

import com.restocore.restocore_api.enums.UserType;

import java.util.UUID;

public record CreateUserResponseDTO(
        UUID id,
        String name,
        String email,
        UserType userType

) {
}
