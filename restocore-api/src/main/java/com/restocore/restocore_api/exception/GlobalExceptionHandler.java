package com.restocore.restocore_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ProblemDetail handleEmailAlreadyRegistered(EmailAlreadyRegisteredException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(LoginAlreadyRegisteredException.class)
    public ProblemDetail handleLoginAlreadyRegistered(LoginAlreadyRegisteredException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationError(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "))
                .trim();

        if (detail.isBlank()) {
            detail = ex.getBindingResult().getGlobalErrors().stream()
                    .map(error -> error.getObjectName() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining("; "))
                    .trim();
        }

        if (detail.isBlank()) {
            detail = "Invalid request data";
        }

        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUnexpectedError(Exception ex) {
        return ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected internal error"
        );
    }
}
