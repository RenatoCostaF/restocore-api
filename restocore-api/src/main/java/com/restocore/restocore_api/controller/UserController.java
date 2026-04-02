package com.restocore.restocore_api.controller;

import com.restocore.restocore_api.dtos.CreateUserRequestDTO;
import com.restocore.restocore_api.dtos.CreateUserResponseDTO;
import com.restocore.restocore_api.dtos.GetAllUserResponseDTO;
import com.restocore.restocore_api.usecase.CreateUserUseCase;
import com.restocore.restocore_api.usecase.GetAllUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetAllUserUseCase getAllUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, GetAllUserUseCase getAllUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getAllUserUseCase = getAllUserUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> create(
            @Valid @RequestBody CreateUserRequestDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(createUserUseCase.execute(request));
    }

    @GetMapping
    public ResponseEntity<List<GetAllUserResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK.value()).body(getAllUserUseCase.execute());
    }
}
