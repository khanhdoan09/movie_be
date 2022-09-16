package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.model.ReviewComment;
import com.example.web_stream_movie_be.model.response.StringResponse;
import com.example.web_stream_movie_be.model.temporary.Temporary;
import com.example.web_stream_movie_be.repository.ReviewCommentRepository;
import com.example.web_stream_movie_be.service.ReviewCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReviewCommentController {
    @Autowired
    private Temporary temporary;

    @Autowired
    private ReviewCommentService reviewCommentService;

    @PostMapping("/comment/review/submit")
    public ResponseEntity<StringResponse> submitReviewComment(@ModelAttribute ReviewComment reviewComment) {
        StringResponse stringResponse = new StringResponse();
        reviewComment.setUserId(temporary.getIdUser());
        this.reviewCommentService.insertReviewComment(reviewComment);
        stringResponse.setResponse("ok");
        return ResponseEntity.ok().body(stringResponse);
    }

    @GetMapping("/comment/review/count/{commentId}/{reviewId}")
    public ResponseEntity<StringResponse> getCountLikeComment(@PathVariable String commentId, @PathVariable int reviewId) {
        StringResponse stringResponse = new StringResponse();
        stringResponse.setResponse(Integer.toString(this.reviewCommentService.countReviewComment(commentId, reviewId)));
        return ResponseEntity.ok().body(stringResponse);
    }

    @GetMapping("/comment/review/check/{commentId}/{review}")
    public ResponseEntity<StringResponse> doesCustomerReviewed(@PathVariable String commentId, @PathVariable String review) {
        StringResponse stringResponse = new StringResponse();
        stringResponse.setResponse(Integer.toString(this.reviewCommentService.doesCustomerReviewed(commentId, temporary.getIdUser(), review)));
        return ResponseEntity.ok().body(stringResponse);
    }
}
