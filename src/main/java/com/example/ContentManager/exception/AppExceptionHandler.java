package com.example.ContentManager.exception;


import com.example.ContentManager.model.output.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ArtistNotFoundException.class)
    public ResponseEntity<APIResponse<Void>> handleArtistNotFoundException(ArtistNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(ArtistSaveException.class)
    public ResponseEntity<APIResponse<Void>> handleArtistSaveException(ArtistSaveException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(ArtistUpdateException.class)
    public ResponseEntity<APIResponse<Void>> handleArtistUpdateException(ArtistUpdateException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(CharacterNotFoundException.class)
    public ResponseEntity<APIResponse<Void>> handleCharacterNotFoundException(CharacterNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(CharacterSaveException.class)
    public ResponseEntity<APIResponse<Void>> handleCharacterSaveException(CharacterSaveException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(StoryNotFoundException.class)
    public ResponseEntity<APIResponse<Void>> handleStoryNotFoundException(StoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(StoryUpdateException.class)
    public ResponseEntity<APIResponse<Void>> handleStoryUpdateException(StoryUpdateException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(StoryDeleteException.class)
    public ResponseEntity<APIResponse<Void>> handleStoryDeleteException(StoryDeleteException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(StorySaveException.class)
    public ResponseEntity<APIResponse<Void>> handleStorySaveException(StorySaveException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(DuplicateStoryTitleException.class)
    public ResponseEntity<APIResponse<Void>> handleDuplicateStoryTitleException(DuplicateStoryTitleException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(DuplicateCharacterNameException.class)
    public ResponseEntity<APIResponse<Void>> handleDuplicateCharacterNameException(DuplicateCharacterNameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Void>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIResponse<>(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        APIResponse<Map<String, String>> response = new APIResponse<>(
                errors,
                "Validation failed",
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.badRequest().body(response);
    }

}
