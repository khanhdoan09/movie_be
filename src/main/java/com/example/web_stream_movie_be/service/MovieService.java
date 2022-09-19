package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.model.Movie;
import com.example.web_stream_movie_be.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    public int getNumberAllMovie() {
        return (int) this.movieRepository.count();
    }
}
