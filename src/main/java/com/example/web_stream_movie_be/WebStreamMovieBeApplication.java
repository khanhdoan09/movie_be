package com.example.web_stream_movie_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class WebStreamMovieBeApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebStreamMovieBeApplication.class, args);
    }

}
