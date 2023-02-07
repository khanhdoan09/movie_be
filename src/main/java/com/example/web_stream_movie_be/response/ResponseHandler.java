package com.example.web_stream_movie_be.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity response(String message, HttpStatus status, Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status);
        map.put("data", data);
        return new ResponseEntity(map, status);
    }
}
