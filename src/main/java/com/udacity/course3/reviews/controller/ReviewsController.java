package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.domain.product.Product;
import com.udacity.course3.reviews.domain.product.ProductRepository;
import com.udacity.course3.reviews.domain.review.Review;
import com.udacity.course3.reviews.domain.review.ReviewRepository;
import com.udacity.course3.reviews.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewsController(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
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
    @PostMapping("/products/{productId}")
    public ResponseEntity<?> createReviewForProduct(@Valid @PathVariable("productId") Long productId,
                                                    @Valid @RequestBody Review review) {
        Product product = productRepository.findById(productId).orElseThrow(NotFoundException::new);
        review.setProduct(product);
        reviewRepository.save(review);

        return new ResponseEntity<>( review, HttpStatus.CREATED);
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<List<Review>> listReviewsForProduct(@Valid  @PathVariable("productId") Long productId) {
        return ResponseEntity.ok(reviewRepository.findAllByProduct(new Product(productId)));
    }
}
