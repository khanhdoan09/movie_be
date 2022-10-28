package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.model.User;
import com.example.web_stream_movie_be.model.response.AuthenticationResponse;
import com.example.web_stream_movie_be.model.response.StringResponse;

import com.example.web_stream_movie_be.model.CustomUserDetails;
import com.example.web_stream_movie_be.security.jwt.JwtTokenProvider;
import com.example.web_stream_movie_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping("/test")
    public String get() {return "test";
    }

    @RequestMapping("/requestLogin")
    public StringResponse requestLogin() {
        return new StringResponse("need login");
    }

    @PostMapping("/register")
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

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ResponseEntity<StringResponse> customLogout(HttpServletRequest request, HttpServletResponse response) {
        // Get the Spring Authentication object of the current request.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        StringResponse stringResponse = new StringResponse();
        stringResponse.setMessage("ok");
        return ResponseEntity.ok().body(stringResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@ModelAttribute User user) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        try {
            // UsernamePasswordAuthenticationToken gets {username, password} from login Request,
            // AuthenticationManager will use it to authenticate a login account
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            authenticationResponse.setMessage("ok");
            authenticationResponse.setJwt(jwt);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            authenticationResponse.setMessage("error");
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
        return ResponseEntity.ok().body(authenticationResponse);
    }


}
