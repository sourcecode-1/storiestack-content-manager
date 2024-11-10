package com.example.ContentManager.model.output;

import lombok.Data;

@Data
public class APIResponse<T> {
    private T data;
    private String message;
    private int status;

    public APIResponse(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }
}
