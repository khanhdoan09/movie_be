package com.example.web_stream_movie_be.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String reviewCommentId;
    private String commentId;
    private long userId;
    private int review;
}
