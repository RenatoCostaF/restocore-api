package com.restocore.restocore_api.exception;

public class LoginAlreadyRegisteredException extends RuntimeException {
    public LoginAlreadyRegisteredException() {
        super("Login already registered");
    }
}
