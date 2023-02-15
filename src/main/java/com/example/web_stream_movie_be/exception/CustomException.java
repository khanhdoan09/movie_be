package com.example.web_stream_movie_be.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception{

    private final HttpStatus status;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
