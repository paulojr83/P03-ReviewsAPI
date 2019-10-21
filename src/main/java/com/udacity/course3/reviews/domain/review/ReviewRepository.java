package com.udacity.course3.reviews.domain.review;

import com.udacity.course3.reviews.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findAllByProduct(Product product);
}
