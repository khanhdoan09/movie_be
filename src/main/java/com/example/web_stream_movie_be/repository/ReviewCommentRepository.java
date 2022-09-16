package com.example.web_stream_movie_be.repository;

import com.example.web_stream_movie_be.model.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, String> {
    @Query(value = "select count(comment_id) from review_comment where comment_id= :commentId and review= :review", nativeQuery = true)
    int countReviewComment(String commentId, int review);
    @Query(value = "select count(comment_id) from review_comment where comment_id= :commentId and user_id= :userId and review= :review", nativeQuery = true)
    int existsByCommentIdAndUserIdAndReview(String commentId, String userId, String review);
}