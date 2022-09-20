package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.model.response.ListObjectResponse;
import com.example.web_stream_movie_be.model.response.MovieListResponse;
import com.example.web_stream_movie_be.model.response.PaginationResponse;
import com.example.web_stream_movie_be.service.CategoryService;
import com.example.web_stream_movie_be.service.CountryService;
import com.example.web_stream_movie_be.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<MovieListResponse> getAllMovies() {
        MovieListResponse listResponse = new MovieListResponse();
        listResponse.setItems(this.movieService.getAllMovies());
        int count = this.movieService.getNumberAllMovie();
        PaginationResponse paginationResponse = new PaginationResponse(1, count, 24);
        listResponse.setPagination(paginationResponse);
        return ResponseEntity.ok().body(listResponse);
    }

    @GetMapping("/country/list")
    public ListObjectResponse getAllCountries() {
        ListObjectResponse listObjectResponse = new ListObjectResponse();
        listObjectResponse.setMessage(countryService.getAllCountries());
        return listObjectResponse;
    }

    @GetMapping("/category/list")
    public ListObjectResponse getAllCategory() {
        ListObjectResponse listObjectResponse = new ListObjectResponse();
        listObjectResponse.setMessage(categoryService.getAllCategories());
        return listObjectResponse;
    }
}
