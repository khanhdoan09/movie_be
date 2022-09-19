package com.example.web_stream_movie_be.model.response;

import lombok.Data;

import java.util.List;

@Data
public class MovieListResponse {
    private List items;
    private PaginationResponse pagination;
}
