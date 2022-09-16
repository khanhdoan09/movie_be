package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.model.User;
import com.example.web_stream_movie_be.model.response.StringResponse;
import com.example.web_stream_movie_be.model.temporary.Temporary;
import com.example.web_stream_movie_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Temporary temporary;

    @GetMapping("/test")
    public String get() {return "test";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<StringResponse> registerUser(@ModelAttribute User user) {
        StringResponse stringResponse = new StringResponse();
        boolean isExist = userService.isUsernameExist(user.getUsername());
        if (isExist) {
            stringResponse.setResponse("error: username is exist");
        }
        else {
            userService.insertUser(user);
            stringResponse.setResponse("ok");
        }
        return ResponseEntity.ok().body(stringResponse);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<StringResponse> loginUser(@ModelAttribute User user) {
        StringResponse stringResponse = new StringResponse();
        boolean isUsernameExist = userService.isUsernameExist(user.getUsername());
        if (!isUsernameExist) {
            stringResponse.setResponse("error: username is not exist");
        }
        else {
            User doesUsernamePasswordCorrect = userService.doesUsernamePasswordCorrect(user.getUsername(), user.getPassword());
            String result =  "wrong password";
            if (doesUsernamePasswordCorrect != null) {
                result =  "ok";
                temporary.setIdUser(doesUsernamePasswordCorrect.getId());
            }
            else {
                result =  "wrong password";
            }
            stringResponse.setResponse(result);

        }
        return ResponseEntity.ok().body(stringResponse);
    }


}
