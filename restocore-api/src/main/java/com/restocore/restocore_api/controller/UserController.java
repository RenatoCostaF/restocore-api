package com.restocore.restocore_api.controller;

import com.restocore.restocore_api.dtos.CreateUserRequestDTO;
import com.restocore.restocore_api.dtos.CreateUserResponseDTO;
import com.restocore.restocore_api.usecase.CreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> create(
            @Valid @RequestBody CreateUserRequestDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(createUserUseCase.execute(request));
    }
}
