package com.udacity.course3.reviews.domain.review;

import com.udacity.course3.reviews.domain.comment.Comment;
import com.udacity.course3.reviews.domain.product.Product;

import javax.persistence.*;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	private int score;

	@OneToOne
	private Comment comment;

	@ManyToOne
	private Product product;

	public Review() {
	}

	public Review(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
