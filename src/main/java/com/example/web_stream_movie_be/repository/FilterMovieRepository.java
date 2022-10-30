package com.example.web_stream_movie_be.repository;

import com.example.web_stream_movie_be.entity.Movie;

import java.util.List;

public interface FilterMovieRepository {
    public List<Movie> getListMovieByFilter(String country, String year, String category);

}
