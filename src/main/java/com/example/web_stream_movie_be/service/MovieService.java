package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.dto.MovieDto;
import com.example.web_stream_movie_be.entity.Movie;
import com.example.web_stream_movie_be.repository.MovieRepository;
import com.example.web_stream_movie_be.repository.impl.FilterMovieRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private FilterMovieRepositoryImpl filterMovieRepository;

    public List<MovieDto> getAllMovies() {
        List<Movie> result = this.movieRepository.findAll();
        return result.stream().map(movie -> mapper.map(movie, MovieDto.class)).collect(Collectors.toList());
    }

    public int getNumberAllMovie() {
        return (int) this.movieRepository.count();
    }

    public List<MovieDto> getListMoviesByName(String movieName) {
        List<Movie> result =  this.movieRepository.findByName(movieName);
        return result.stream().map(movie -> mapper.map(movie, MovieDto.class)).collect(Collectors.toList());

    }

    public int countMovieByName(String movieName) {
        return this.movieRepository.countByName(movieName);
    }

    public List<MovieDto> getListMoviesByFilter(String country, String year, String category) {
        List<Movie> result =  this.filterMovieRepository.getListMovieByFilter(country, year, category);
        return result.stream().map(movie -> mapper.map(movie, MovieDto.class)).collect(Collectors.toList());
    }
}
