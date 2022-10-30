package com.example.web_stream_movie_be.service;

import com.example.web_stream_movie_be.entity.ReviewComment;
import com.example.web_stream_movie_be.repository.ReviewCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewCommentService {
    @Autowired
    private ReviewCommentRepository reviewCommentRepository;
    public void insertReviewComment(ReviewComment reviewComment) {
        this.reviewCommentRepository.save(reviewComment);
    }
    public int countReviewComment(String commentId, int review) {
        return this.reviewCommentRepository.countReviewComment(commentId, review);
    }
    public int doesCustomerReviewed(String commentId, long userId, String review) {
        return this.reviewCommentRepository.existsByCommentIdAndUserIdAndReview(commentId, userId, review);
    }
}
