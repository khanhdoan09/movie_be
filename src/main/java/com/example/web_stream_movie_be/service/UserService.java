package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.model.User;
import com.example.web_stream_movie_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void insertUser(User user) {
        this.userRepository.save(user);
    }

    public boolean isUsernameExist(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public User doesUsernamePasswordCorrect(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

}
