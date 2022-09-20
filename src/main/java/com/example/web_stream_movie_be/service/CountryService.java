package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.model.Country;
import com.example.web_stream_movie_be.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
