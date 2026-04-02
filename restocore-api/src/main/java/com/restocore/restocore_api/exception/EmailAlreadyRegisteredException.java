package com.restocore.restocore_api.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException() {
        super("Email already registered");
    }
}
