package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.comment.Comment;
import com.udacity.course3.reviews.domain.comment.CommentRepository;
import com.udacity.course3.reviews.domain.review.Review;
import com.udacity.course3.reviews.domain.review.ReviewRepository;
import com.udacity.course3.reviews.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    private CommentRepository commenRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public CommentsController(CommentRepository commenRepository, ReviewRepository reviewRepository) {
        this.commenRepository = commenRepository;
        this.reviewRepository = reviewRepository;
    }

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<Comment> createCommentForReview(@PathVariable("reviewId") Long reviewId, @RequestBody Comment comment) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(NotFoundException::new);
        comment.setReview(review);
        commenRepository.save(comment);
        return new ResponseEntity<>( comment, HttpStatus.CREATED);
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> listCommentsForReview(@PathVariable("reviewId") Long reviewId) {
        return ResponseEntity.ok(commenRepository.findAllByReview(new Review(reviewId)));
    }
}