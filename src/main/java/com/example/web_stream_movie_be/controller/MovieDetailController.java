package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.dto.CategoryDto;
import com.example.web_stream_movie_be.entity.Movie;
import com.example.web_stream_movie_be.entity.MovieDetail;
import com.example.web_stream_movie_be.entity.response.MovieDetailResponse;
import com.example.web_stream_movie_be.response.ResponseHandler;
import com.example.web_stream_movie_be.service.MovieDetailService;
import com.example.web_stream_movie_be.service.MovieService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.util.*;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping("/api/movie/detail")
public class MovieDetailController {
    @Autowired
    private MovieDetailService movieDetailService;
    @Autowired
    private MovieService movieService;

    @GetMapping("/getAll/{movieId}")
    @ApiResponses({
            @ApiResponse(responseCode  = "200", description = "Found list all category", content = @Content(schema = @Schema(implementation = MovieDetail.class))),
            @ApiResponse(responseCode  = "404", description = "Not found list all category")
    })
    public ResponseEntity getAllMovieDetail(@PathVariable String movieId) {
        Optional<MovieDetail> movieDetail = this.movieDetailService.getAllMovieDetail(movieId);
        return movieDetail.map(m -> {
            MovieDetailResponse movieDetailResponse = new MovieDetailResponse();
            Movie movie = m.getMovie();
            movie.setNumberVisit(movie.getNumberVisit() + 1);
            movieService.setNumberVisit(movie);
            movieDetailResponse.setMessage(m);
            return ResponseHandler.response("get data movie detail successfully", HttpStatus.ACCEPTED, movieDetail);
        }).orElseThrow(() -> new NotFoundException("not found movie detail"));
    }
}
