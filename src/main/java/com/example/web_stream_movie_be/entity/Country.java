package com.example.web_stream_movie_be.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String countryId;
    private String name;

    public String getCountryId() {
        return this.countryId;
    }

    public String getName() {
        return this.name;
    }
}
