package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.controller.authentication.Entry;
import com.example.web_stream_movie_be.controller.authentication.IAuthentication;
import com.example.web_stream_movie_be.controller.authentication.SignInImpl;
import com.example.web_stream_movie_be.controller.authentication.SignUpImpl;
import com.example.web_stream_movie_be.dto.GooglePojo;
import com.example.web_stream_movie_be.entity.User;
import com.example.web_stream_movie_be.entity.response.AuthenticationResponse;
import com.example.web_stream_movie_be.entity.response.StringResponse;

import com.example.web_stream_movie_be.entity.CustomUserDetails;
import com.example.web_stream_movie_be.exception.CustomException;
import com.example.web_stream_movie_be.security.jwt.JwtTokenProvider;
import com.example.web_stream_movie_be.service.UserService;
import com.example.web_stream_movie_be.utils.GoogleUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private SignUpImpl signUp;

    @Autowired
    private SignInImpl signIn;

    @Autowired
    private GoogleUtils googleUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/requestLogin")
    public StringResponse requestLogin() {
        return new StringResponse("need login");
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user) throws CustomException {
        userService.existByEmail(user.getEmail(), signUp);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.insertUser(user);
        return "ok";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@Valid @ModelAttribute User user) throws CustomException {
        userService.existByEmail(user.getEmail(), signIn);
        AuthenticationResponse authenticationResponse = userService.createAuthentication(
                authenticationManager, user.getEmail(), user.getPassword());
        return ResponseEntity.ok().body(authenticationResponse);
    }

    @RequestMapping("/login_google")
    public AuthenticationResponse loginGoogle(HttpServletRequest request) throws IOException, CustomException {
        String code = Optional.ofNullable(request.getParameter("code"))
                .map(i -> i.isEmpty() ? null : i)
                .orElseThrow(() -> new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "cannot login"));
        String accessToken = googleUtils.getToken(code);
        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);

        boolean isExist = userService.isEmailExist(googlePojo.getEmail());
        if (!isExist) {
            User user = new User(googlePojo.getEmail(), googlePojo.getId(), false);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.insertUser(user);
        }
        AuthenticationResponse authenticationResponse = userService.createAuthentication(
                authenticationManager, googlePojo.getEmail(), googlePojo.getId());
//        UserDetails userDetail = googleUtils.buildUser(googlePojo);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
//                userDetail.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authenticationResponse;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String customLogout(HttpServletRequest request, HttpServletResponse response) {
        // Get the Spring Authentication object of the current request.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "ok";
    }

}
