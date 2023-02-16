package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.controller.authentication.Entry;
import com.example.web_stream_movie_be.controller.authentication.IAuthentication;
import com.example.web_stream_movie_be.controller.authentication.SignInImpl;
import com.example.web_stream_movie_be.controller.authentication.SignUpImpl;
import com.example.web_stream_movie_be.entity.User;
import com.example.web_stream_movie_be.entity.response.AuthenticationResponse;
import com.example.web_stream_movie_be.entity.response.StringResponse;

import com.example.web_stream_movie_be.entity.CustomUserDetails;
import com.example.web_stream_movie_be.exception.CustomException;
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
import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private Entry entry;

    @Autowired
    private SignUpImpl signUp;

    @Autowired
    private SignInImpl signIn;

    @RequestMapping("/requestLogin")
    public StringResponse requestLogin() {
        return new StringResponse("need login");
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user) throws CustomException {
        existByEmail(user.getEmail(), signUp);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.insertUser(user);
        return "ok";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@Valid @ModelAttribute User user) throws CustomException {
        existByEmail(user.getEmail(), signIn);
//      User doesUsernamePasswordCorrect = userService.doesUsernamePasswordCorrect(user.getUsername(), user.getPassword());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        try {
            // UsernamePasswordAuthenticationToken gets {username, password} from login Request,
            // AuthenticationManager will use it to authenticate a login account
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
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
        return ResponseEntity.ok().body(authenticationResponse);
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
        System.out.println();
        return ResponseEntity.ok().body(stringResponse);
    }

    private void existByEmail(String email, IAuthentication iAuthentication) throws CustomException {
        entry.setAuthentication(iAuthentication);
        entry.getAuthentication().existByEmail(email);
    }
}
