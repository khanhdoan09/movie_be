package com.example.web_stream_movie_be.dto;

import com.example.web_stream_movie_be.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class CommentDto {
    private String commentId;
    private User userId;
    private String commentParentId;
    private String movieId;
    private String content;
    private String createAt;
}
