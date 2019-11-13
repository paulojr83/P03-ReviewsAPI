package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.domain.review.ReviewDocument;

public interface ReviewMongoService {

	public void saveReview( ReviewDocument jpaReview);

	public ReviewDocument retrieveReviewById(Long reviewId);
}
