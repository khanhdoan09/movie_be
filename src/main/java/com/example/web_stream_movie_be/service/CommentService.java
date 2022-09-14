package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.model.Comment;
import com.example.web_stream_movie_be.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public void insertComment(Comment comment) {
        this.commentRepository.save(comment);
    }

    public List<Comment> getListCommentByMovie(String movieId) {
        return this.commentRepository.findCommentsByMovieId(movieId);
    }
}
