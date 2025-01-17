package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.domain.review.ReviewDocument;
import com.udacity.course3.reviews.repository.ReviewMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMongoServiceImpl implements ReviewMongoService {

	private final ReviewMongoRepository reviewMongoRepository;

	public ReviewMongoServiceImpl(ReviewMongoRepository reviewMongoRepository) {
		this.reviewMongoRepository = reviewMongoRepository;
	}

	@Override
	public void saveReview(ReviewDocument jpaReview) {
		ReviewDocument mongoReview =
				new ReviewDocument();
		mongoReview.setReviewId(jpaReview.getReviewId());
		mongoReview.setReviewTitle(jpaReview.getReviewTitle());
		mongoReview.setReviewText(jpaReview.getReviewText());
		mongoReview.setRecommended(jpaReview.isRecommended());
		reviewMongoRepository.save(mongoReview);
	}

	@Override
	public ReviewDocument retrieveReviewById(String reviewId) {
		ReviewDocument mongoReview = reviewMongoRepository.findById(reviewId)
				.orElse(null);
		return mongoReview;
	}
}
