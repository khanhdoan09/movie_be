package com.example.web_stream_movie_be.repository;

import com.example.web_stream_movie_be.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
     List<Comment> findCommentsByMovieIdOrderByCreateAtDesc(String movieId);
     int countByMovieId(String movieId);
}