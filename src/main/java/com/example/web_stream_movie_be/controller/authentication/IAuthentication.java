package com.example.web_stream_movie_be.controller.authentication;

import com.example.web_stream_movie_be.exception.CustomException;

public interface IAuthentication {
    void existByEmail(String Email) throws CustomException;
}
