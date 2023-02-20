package com.example.web_stream_movie_be.controller;

import com.example.web_stream_movie_be.entity.ReviewComment;
import com.example.web_stream_movie_be.entity.response.StringResponse;
import com.example.web_stream_movie_be.entity.temporary.Temporary;
import com.example.web_stream_movie_be.service.ReviewCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment/review")
public class ReviewCommentController {
    @Autowired
    private Temporary temporary;

    @Autowired
    private ReviewCommentService reviewCommentService;

    @PostMapping("/submit")
    public ResponseEntity<StringResponse> submitReviewComment(@ModelAttribute ReviewComment reviewComment) {
        StringResponse stringResponse = new StringResponse();
        reviewComment.setUserId(temporary.getIdUser());
        this.reviewCommentService.insertReviewComment(reviewComment);
        stringResponse.setMessage("ok");
        return ResponseEntity.ok().body(stringResponse);
    }

    @GetMapping("/count/{commentId}/{reviewId}")
    public int getCountLikeComment(@PathVariable String commentId, @PathVariable int reviewId) {
        return reviewCommentService.countReviewComment(commentId, reviewId);
    }

    @GetMapping("/check/{commentId}/{review}")
    public ResponseEntity<StringResponse> doesCustomerReviewed(@PathVariable String commentId, @PathVariable String review) {
        StringResponse stringResponse = new StringResponse();
        stringResponse.setMessage(Integer.toString(this.reviewCommentService.doesCustomerReviewed(commentId, temporary.getIdUser(), review)));
        return ResponseEntity.ok().body(stringResponse);
    }
}
