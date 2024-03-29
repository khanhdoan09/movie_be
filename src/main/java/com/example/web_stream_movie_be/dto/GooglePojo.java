package com.example.web_stream_movie_be.dto;

import lombok.Data;

@Data
public class GooglePojo {
    private String id;
    private String email;
    private boolean verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String link;
    private String picture;
    // getter-setter
}