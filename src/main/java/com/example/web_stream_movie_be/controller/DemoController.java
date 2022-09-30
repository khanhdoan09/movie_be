package com.example.web_stream_movie_be.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DemoController {
    @GetMapping("/")
    public String demo() {
        return "demo";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/login_form")
    public String login() {
        return "login";
    }
}
