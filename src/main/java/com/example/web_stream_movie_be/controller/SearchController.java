package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.model.response.MovieListResponse;
import com.example.web_stream_movie_be.model.response.PaginationResponse;
import com.example.web_stream_movie_be.model.temporary.Temporary;
import com.example.web_stream_movie_be.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/movie")
public class SearchController {
    @Autowired
    private MovieService movieService;
    @GetMapping("/search/{movieName}")
    public MovieListResponse getListMovieByName(@PathVariable String movieName) {
        MovieListResponse movieListResponse = new MovieListResponse();
        movieListResponse.setItems(this.movieService.getListMoviesByName(movieName));
        PaginationResponse paginationResponse = new PaginationResponse(1, this.movieService.countMovieByName(movieName), 24);
        movieListResponse.setPagination(paginationResponse);
        return movieListResponse;
    }

    @GetMapping("/filter")
    public MovieListResponse getListMovieByFilter(@RequestParam Map<String, String> req) {
        String yearOption = req.get("year");
        String categoryOption = req.get("category");
        String countryOption = req.get("country");

        MovieListResponse movieListResponse = new MovieListResponse();
        movieListResponse.setItems(this.movieService.getListMoviesByFilter(countryOption, yearOption, categoryOption));
        return movieListResponse;
    }
}
