package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.product.Product;
import com.udacity.course3.reviews.domain.review.ReviewDocument;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.domain.review.Review;
import com.udacity.course3.reviews.repository.ReviewRepository;
import com.udacity.course3.reviews.service.ReviewMongoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ReviewMongoService reviewMongoService;

    public ReviewsController(ReviewRepository reviewRepository, ProductRepository productRepository, ReviewMongoService reviewMongoService) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.reviewMongoService = reviewMongoService;
    }

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId,
                                                    @RequestBody Review review ) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            review.setProduct(product);
            Review createdReview = reviewRepository.save(review);

            reviewMongoService.saveReview(createdReview);

            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<?>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            List<Review> allJpaReviews = reviewRepository.findAllByProduct(product);
            List<ReviewDocument> allMongoReviews = new ArrayList<>();

            for (Review review : allJpaReviews) {
                ReviewDocument mongoReview =
                        reviewMongoService.retrieveReviewById(review.getReviewId());
                allMongoReviews.add(mongoReview);
            }
            return new ResponseEntity<>(allMongoReviews, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
