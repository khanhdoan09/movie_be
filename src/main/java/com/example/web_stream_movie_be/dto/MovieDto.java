package com.example.web_stream_movie_be.dto;

import com.example.web_stream_movie_be.entity.Category;
import com.example.web_stream_movie_be.entity.Country;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.Collection;

@Data
public class MovieDto {
    private String name;
    private String originName;
    private String slug;
    private String posterUrl;
    private String status;
    private String quality;
    private int year;
    private Country country;
    private Collection<Category> categories;
}
