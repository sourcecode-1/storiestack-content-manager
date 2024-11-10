package com.example.ContentManager.exception;

public class StorySaveException extends RuntimeException {
    public StorySaveException(String message, Throwable cause) {
        super(message, cause);
    }
}