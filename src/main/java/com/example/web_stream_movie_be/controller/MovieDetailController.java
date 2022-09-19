package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.model.MovieDetail;
import com.example.web_stream_movie_be.model.response.MovieDetailResponse;
import com.example.web_stream_movie_be.service.MovieDetailService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie/detail")
public class MovieDetailController {
    @Autowired
    private MovieDetailService movieDetailService;

    @GetMapping("/brief/{movieId}")
    // status, nation, quality
    public ResponseEntity<MovieDetailResponse> getBriefMovieDetail(@PathVariable  String movieId) {
        MovieDetailResponse movieDetailResponse = new MovieDetailResponse();
        MovieDetail movieDetail = this.movieDetailService.getBriefMovieDetail(movieId);
        movieDetailResponse.setMessage(movieDetail);
        return ResponseEntity.ok().body(movieDetailResponse);
    }

    @GetMapping("/getAll/{movieId}")
    public ResponseEntity<MovieDetailResponse> getAllMovieDetail(@PathVariable  String movieId) {
        MovieDetailResponse movieDetailResponse = new MovieDetailResponse();
        MovieDetail movieDetail = this.movieDetailService.getAllMovieDetail(movieId);
        movieDetailResponse.setMessage(movieDetail);
        return ResponseEntity.ok().body(movieDetailResponse);
    }
}
