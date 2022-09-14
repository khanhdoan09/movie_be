package com.example.web_stream_movie_be.model.response;

import lombok.Data;

@Data
public class StringResponse {
    private String response;
    public StringResponse() {

    }
    public StringResponse(String response) {
        this.response = response;
    }
}

