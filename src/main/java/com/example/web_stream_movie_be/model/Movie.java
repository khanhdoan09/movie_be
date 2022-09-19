package com.example.web_stream_movie_be.model;

import com.google.gson.Gson;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String movieId;
    private String name;
    private String originName;
    private String slug;
    private String _id;
}
