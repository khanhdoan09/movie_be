package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.entity.response.MovieListResponse;
import com.example.web_stream_movie_be.entity.response.PaginationResponse;
import com.example.web_stream_movie_be.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Map;

@RestController
@RequestMapping("/api/movie")
@Tag(name = "search and filter")
public class SearchController {
    @Autowired
    private MovieService movieService;

    @Operation(description = "Search list movie by name", responses = {
            @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieListResponse.class))), responseCode = "200") })
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Found list movie by name"),
            @ApiResponse(responseCode  = "404", description = "not found list movie by name")
    })
    @GetMapping("/search/{movieName}")
    public MovieListResponse getListMovieByName(@PathVariable String movieName) {
        MovieListResponse movieListResponse = new MovieListResponse();
        movieListResponse.setItems(this.movieService.getListMoviesByName(movieName));
        PaginationResponse paginationResponse = new PaginationResponse(1, this.movieService.countMovieByName(movieName), 24);
        movieListResponse.setPagination(paginationResponse);
        if (!movieListResponse.getItems().isEmpty())
            return movieListResponse;
        else
            throw new NotFoundException("not found list movie by specified name");
    }

    @Operation(description = "filter list movie by year, category, country")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Found list movie by filter", content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieListResponse.class)))),
            @ApiResponse(responseCode  = "404", description = "not found list movie by filter")
    })
    @GetMapping("/filter")
    public MovieListResponse getListMovieByFilter(@RequestParam Map<String, String> req) {
        String yearOption = req.get("year");
        String categoryOption = req.get("category");
        String countryOption = req.get("country");
        MovieListResponse movieListResponse = new MovieListResponse();
        movieListResponse.setItems(this.movieService.getListMoviesByFilter(countryOption, yearOption, categoryOption));
        if (!movieListResponse.getItems().isEmpty())
            return movieListResponse;
        else
            throw new NotFoundException("not found list movie by specified filter");
    }
}
