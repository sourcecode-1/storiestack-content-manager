package com.example.ContentManager.exception;

public class ArtistSaveException extends RuntimeException {
    public ArtistSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}