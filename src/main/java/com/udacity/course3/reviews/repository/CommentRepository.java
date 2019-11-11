package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.comment.Comment;
import com.udacity.course3.reviews.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findAllByReview(Review review);
}
