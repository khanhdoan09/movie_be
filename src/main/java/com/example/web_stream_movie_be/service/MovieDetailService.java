package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.entity.MovieDetail;
import com.example.web_stream_movie_be.repository.MovieDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieDetailService {

    @Autowired
    private MovieDetailRepository movieDetailRepository;

    public Optional<MovieDetail> getAllMovieDetail(String slug) {
        return this.movieDetailRepository.getAllById(slug);
    }

}
