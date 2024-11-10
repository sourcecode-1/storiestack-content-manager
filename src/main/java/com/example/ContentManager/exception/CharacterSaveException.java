package com.example.ContentManager.exception;

public class CharacterSaveException extends RuntimeException {
    public CharacterSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}