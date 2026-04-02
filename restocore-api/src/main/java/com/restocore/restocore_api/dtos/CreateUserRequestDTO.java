package com.restocore.restocore_api.dtos;

import com.restocore.restocore_api.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Login is required")
        String login,

        @NotBlank(message = "Password is required")
        String password,

        @NotNull(message = "User type is required")
        UserType userType,

        @NotBlank(message = "Street is required")
        String street,

        @NotBlank(message = "Number is required")
        String number,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "Zip code is required")
        String zipCode
) {
}
