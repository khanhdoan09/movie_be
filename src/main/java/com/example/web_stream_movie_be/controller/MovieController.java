package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.dto.CategoryDto;
import com.example.web_stream_movie_be.dto.CountryDto;
import com.example.web_stream_movie_be.entity.Category;
import com.example.web_stream_movie_be.entity.Country;
import com.example.web_stream_movie_be.entity.response.MovieListResponse;
import com.example.web_stream_movie_be.entity.response.PaginationResponse;
import com.example.web_stream_movie_be.service.CategoryService;
import com.example.web_stream_movie_be.service.CountryService;
import com.example.web_stream_movie_be.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

@RestController()
@RequestMapping("/api/movie")
@Tag(name = "movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CategoryService categoryService;

    @Operation(description = "get list movie by all")
    @ApiResponses({
            @ApiResponse(responseCode  = "200", description = "Found list all movie"),
            @ApiResponse(responseCode  = "404", description = "Not found list all movie")
    })
    @GetMapping("/list")
    public MovieListResponse getAllMovies() {
        MovieListResponse listResponse = new MovieListResponse();
        listResponse.setItems(this.movieService.getAllMovies());
        int count = this.movieService.getNumberAllMovie();
        PaginationResponse paginationResponse = new PaginationResponse(1, count, 24);
        listResponse.setPagination(paginationResponse);
        if (listResponse.getItems().size() > 0)
            return listResponse;
        else
            throw new NotFoundException("not found list all movie");
    }


    @GetMapping("/country/list")
    @Operation(description = "get list all country")
    @ApiResponses({
            @ApiResponse(responseCode  = "200", description = "Found list all country",  content = @Content(array = @ArraySchema(schema = @Schema(implementation = CountryDto.class)))),
            @ApiResponse(responseCode  = "404", description = "Not found list all country")
    })
    public List<CountryDto> getAllCountries() {
        List<CountryDto> result = countryService.getAllCountries();
        if (result.size() > 0)
            return result;
        else
            throw new NotFoundException("not found list all country");
    }

    @GetMapping("/category/list")
    @ApiResponses({
            @ApiResponse(responseCode  = "200", description = "Found list all category", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode  = "404", description = "Not found list all category")
    })
    public List<CategoryDto> getAllCategory() {
        List<CategoryDto> result = categoryService.getAllCategories();
        if (result.size() > 0)
            return result;
        else
            throw new NotFoundException("not found list all category");
    }
}
