package com.example.web_stream_movie_be.controller.authentication;

import com.example.web_stream_movie_be.exception.CustomException;
import com.example.web_stream_movie_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SignInImpl implements IAuthentication {
    @Autowired
    private UserService userService;
    @Override
    public void existByEmail(String email) throws CustomException {
        boolean isExist = userService.isEmailExist(email);
        if (!isExist) {
            throw new CustomException(HttpStatus.CONFLICT, "error: email is not exist");
        }
    }
}
