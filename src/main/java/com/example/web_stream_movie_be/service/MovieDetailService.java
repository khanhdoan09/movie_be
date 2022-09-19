package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.model.MovieDetail;
import com.example.web_stream_movie_be.repository.MovieDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class MovieDetailService {

    @Autowired
    private MovieDetailRepository movieDetailRepository;

    // status, nation, quality
    public MovieDetail getBriefMovieDetail(String movieId) {
        return this.movieDetailRepository.getBriefMovieDetail(movieId);
    }

    public MovieDetail getAllMovieDetail(String movieId) {
        return this.movieDetailRepository.getAllById(movieId);
    }

}
