package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.model.Movie;
import com.example.web_stream_movie_be.repository.FilterMovieRepository;
import com.example.web_stream_movie_be.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Filter;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private FilterMovieRepository filterMovieRepository;

    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    public int getNumberAllMovie() {
        return (int) this.movieRepository.count();
    }

    public List<Movie> getListMoviesByName(String movieName) {
        return this.movieRepository.findByName(movieName);
    }


    public int countMovieByName(String movieName) {
        return this.movieRepository.countByName(movieName);
    }

    public List<Movie> getListMoviesByFilter(String country, String year, String category) {
        return this.filterMovieRepository.getListMovieByFilter(country, year, category);
    }
}
