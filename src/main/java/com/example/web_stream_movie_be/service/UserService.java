package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.entity.User;
import com.example.web_stream_movie_be.entity.CustomUserDetails;
import com.example.web_stream_movie_be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public void insertUser(User user) {
        this.userRepository.save(user);
    }

    public boolean isUsernameExist(String username) {
        return this.userRepository.existsByUsername(username);
    }

//    public User doesUsernamePasswordCorrect(String username, String password) {
//        return this.userRepository.findByUsernameAndPassword(username, password);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        else {
            CustomUserDetails customUserDetails = new CustomUserDetails(user);
            return customUserDetails;
        }
    }

    public User loadUserById(Long userId) {
        return this.userRepository.findUserById(userId);
    }
}
