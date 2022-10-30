package com.example.web_stream_movie_be.entity.response;

import com.example.web_stream_movie_be.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MovieListResponse {
    private List<Movie> items;
    private PaginationResponse pagination;
}
