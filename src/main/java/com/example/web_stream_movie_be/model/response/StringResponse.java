package com.example.web_stream_movie_be.model.response;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class StringResponse {
    private String message;
    public StringResponse() {

    }
}

