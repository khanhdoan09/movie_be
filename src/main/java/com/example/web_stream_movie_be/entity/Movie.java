package com.example.web_stream_movie_be.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String movieId;
    private String name;
    private String originName;
    private String slug;
    private String posterUrl;
    private String status;
    private String quality;
    private int year;
    private int numberVisit;
    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @ManyToMany
    @JoinTable(name ="movie_detail_category", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Collection<Category> categories;
}
