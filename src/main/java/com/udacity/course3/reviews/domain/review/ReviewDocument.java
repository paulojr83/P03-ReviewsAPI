package com.udacity.course3.reviews.domain.review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("reviews")
public class ReviewDocument {

	@Id
	private Long reviewId;

	private String reviewTitle;

	private String reviewText;

	private boolean recommended;

	public ReviewDocument() {
	}

	public ReviewDocument(Review review) {
		this.reviewTitle = review.getTitle();
		this.reviewText = review.getReviewText();
		this.recommended = review.isRecommended();
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public boolean isRecommended() {
		return recommended;
	}

	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}
}
