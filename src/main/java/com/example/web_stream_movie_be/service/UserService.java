package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.controller.authentication.Entry;
import com.example.web_stream_movie_be.controller.authentication.IAuthentication;
import com.example.web_stream_movie_be.controller.authentication.SignInImpl;
import com.example.web_stream_movie_be.controller.authentication.SignUpImpl;
import com.example.web_stream_movie_be.entity.User;
import com.example.web_stream_movie_be.entity.CustomUserDetails;
import com.example.web_stream_movie_be.entity.response.AuthenticationResponse;
import com.example.web_stream_movie_be.exception.CustomException;
import com.example.web_stream_movie_be.repository.UserRepository;
import com.example.web_stream_movie_be.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Entry entry;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public void insertUser(User user) {
        this.userRepository.save(user);
    }

    public boolean isEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public void existByEmail(String email, IAuthentication iAuthentication) throws CustomException {
        entry.setAuthentication(iAuthentication);
        entry.getAuthentication().existByEmail(email);
    }

    public AuthenticationResponse createAuthentication(AuthenticationManager authenticationManager, String email, String password) throws CustomException {
        try {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            // AuthenticationManager will use it to authenticate a login account
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email, password
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            authenticationResponse.setMessage("ok");
            authenticationResponse.setJwt(jwt);
            return authenticationResponse;
        }catch (Exception e) {
            System.out.println("create jwt: " + e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "cannot create jwt to login");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username);
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
