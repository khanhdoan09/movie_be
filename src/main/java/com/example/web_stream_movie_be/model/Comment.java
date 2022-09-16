package com.example.web_stream_movie_be.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String commentId;
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;
    private String commentParentId;
    private String movieId;
    private String content;
    private String createAt;
}
