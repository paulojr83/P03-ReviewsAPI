package com.udacity.course3.reviews.domain.product;

import com.udacity.course3.reviews.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Review> findAllByProduct(Product product);
}