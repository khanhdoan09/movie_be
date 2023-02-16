package com.example.web_stream_movie_be.controller.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class Entry {
    private IAuthentication authentication;
}
