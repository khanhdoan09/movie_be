package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.dto.CategoryDto;
import com.example.web_stream_movie_be.entity.Category;
import com.example.web_stream_movie_be.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories() {
        List<Category> result = categoryRepository.findAll();
        return  result.stream().map(category -> new CategoryDto(category.getName())).collect(Collectors.toList());
    }
}
