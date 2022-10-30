package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.dto.CountryDto;
import com.example.web_stream_movie_be.entity.Country;
import com.example.web_stream_movie_be.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;
    public List<CountryDto> getAllCountries() {
        List<Country> result = countryRepository.findAll();
        return result.stream().map(country -> new CountryDto(country.getName())).collect(Collectors.toList());
    }
}
