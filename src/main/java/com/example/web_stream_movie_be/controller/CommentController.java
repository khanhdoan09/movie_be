package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.model.Comment;
import com.example.web_stream_movie_be.model.User;
import com.example.web_stream_movie_be.model.response.StringResponse;
import com.example.web_stream_movie_be.model.security.CustomUserDetails;
import com.example.web_stream_movie_be.model.temporary.Temporary;
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

    @Autowired
    private Temporary temporary;

    @GetMapping("/get")
    public ResponseEntity<List<Comment>> getListCommentByMovie(String movieId) {
        return ResponseEntity.ok().body(commentService.getListCommentByMovie(movieId));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCountComment(String movieId) {
        return ResponseEntity.ok().body(commentService.getCountComment(movieId));
    }

    @RequestMapping("/submit")
    public ResponseEntity<StringResponse> submitComment(@ModelAttribute Comment comment) {
        StringResponse stringResponse = new StringResponse();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        comment.setUser(customUserDetails.getUser());
        comment.getUser().setId(customUserDetails.getId());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentTime = formatter.format(date);
        comment.setCreateAt(currentTime);
        this.commentService.insertComment(comment);
        stringResponse.setMessage("ok");
        return ResponseEntity.ok().body(stringResponse);
    }



}
