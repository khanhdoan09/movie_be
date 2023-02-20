package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.dto.CommentDto;
import com.example.web_stream_movie_be.entity.Comment;
import com.example.web_stream_movie_be.entity.User;
import com.example.web_stream_movie_be.entity.response.StringResponse;
import com.example.web_stream_movie_be.entity.CustomUserDetails;
import com.example.web_stream_movie_be.entity.temporary.Temporary;
import com.example.web_stream_movie_be.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/get")
    public List<Comment> getListCommentByMovie(String movieId) {
        return commentService.getListCommentByMovie(movieId);
    }

    @GetMapping("/count")
    public int getCountComment(String movieId) {
        return commentService.getCountComment(movieId);
    }

    @RequestMapping("/submit")
    public ResponseEntity<StringResponse> submitComment(@ModelAttribute Comment comment) {
        StringResponse stringResponse = new StringResponse();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentTime = formatter.format(date);
        comment.setUser(customUserDetails.getUser());
        comment.setCreateAt(currentTime);
        System.out.println(comment);
        commentService.insertComment(comment);
        stringResponse.setMessage("ok");
        return ResponseEntity.ok().body(stringResponse);
    }



}
