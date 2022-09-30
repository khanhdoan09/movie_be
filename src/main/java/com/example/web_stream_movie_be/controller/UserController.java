package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.model.User;
import com.example.web_stream_movie_be.model.response.StringResponse;

import com.example.web_stream_movie_be.security.jwt.JwtTokenProvider;
import com.example.web_stream_movie_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;



    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtTokenProvider tokenProvider;


    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping("/test")
    public String get() {return "test";
    }

    @RequestMapping("/requestLogin")
    public StringResponse requestLogin() {
        return new StringResponse("need login");
    }

    @RequestMapping("/register")
    public ResponseEntity<StringResponse> registerUser(@ModelAttribute User user) {
        StringResponse stringResponse = new StringResponse();
        boolean isExist = userService.isUsernameExist(user.getUsername());
        if (isExist) {
            stringResponse.setMessage("error: username is exist");
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.insertUser(user);
            stringResponse.setMessage("ok");
        }
        return ResponseEntity.ok().body(stringResponse);
    }



    @RequestMapping("/login")
    public ResponseEntity<StringResponse> loginUser(@ModelAttribute User user) {
        System.out.println(user);
        StringResponse stringResponse = new StringResponse();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            stringResponse.setMessage("ok");
        }catch (Exception e) {
            System.out.println(e.getMessage());
            stringResponse.setMessage("error");
        }


        // Trả về jwt cho người dùng.
//        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
//        System.out.println(
//                jwt
//        );

//        boolean isUsernameExist = userService.isUsernameExist(user.getUsername());
//        if (!isUsernameExist) {
//            stringResponse.setMessage("error: username is not exist");
//        }
//        else {
//            User doesUsernamePasswordCorrect = userService.doesUsernamePasswordCorrect(user.getUsername(), user.getPassword());
//            String result =  "wrong password";
//            if (doesUsernamePasswordCorrect != null) {
//                result =  "ok";
//                temporary.setIdUser(doesUsernamePasswordCorrect.getId());
//            }
//            else {
//                result =  "wrong password";
//            }
//            stringResponse.setMessage(result);
//        }
        return ResponseEntity.ok().body(stringResponse);
    }


}
