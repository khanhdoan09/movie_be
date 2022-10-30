package com.example.web_stream_movie_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class CategoryDto {
    private String name;
    private String slug;
}
