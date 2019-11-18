package com.udacity.course3.reviews.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.course3.reviews.domain.product.Product;
import com.udacity.course3.reviews.domain.product.ProductRepository;
import com.udacity.course3.reviews.domain.review.Review;
import com.udacity.course3.reviews.domain.review.ReviewRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ReviewsApplicationTests {

    @Autowired
    private MockMvc mvc;

    private JacksonTester<Review> json;
    private JacksonTester<Product> jsonProduct;

    @MockBean
    private ReviewRepository reviewRepository;

    @MockBean
    private ProductRepository productRepository;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        Review review = getReview();

        given(reviewRepository.save(any())).willReturn(review);
        given(reviewRepository.findById(review.getReviewId())).willReturn(Optional.of(review));
        given(reviewRepository.findAll()).willReturn(Collections.singletonList(review));

        Product product = getProduct();
        given(productRepository.save(any())).willReturn(product);
        given(productRepository.findById(product.getProductId())).willReturn(Optional.of(product));
        given(productRepository.findAll()).willReturn(Collections.singletonList(product));
    }


    @Test
    public void createReviewForProduct() throws Exception {
        Review review = getReview();
        Product product = getProduct();
        mvc.perform(
                post(new URI("/products"))
                        .content(jsonProduct.write(product).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        mvc.perform(
                post(new URI("/reviews/products/1"))
                        .content(json.write(review).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }



    @Test
    public void listReviewsForProduct() throws Exception {
        Review review = getReview();
        Product product = getProduct();
        mvc.perform(
                post(new URI("/products"))
                        .content(jsonProduct.write(product).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        mvc.perform(
                post(new URI("/reviews/products/1"))
                        .content(json.write(review).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        mvc.perform(
                get(new URI("/reviews/products/1"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(result -> hasSize(1))
                .andExpect(status().isOk());
    }

   private Review getReview() {
        Review review = new Review();
        review.setReviewId(1L);
        review.setScore(4);
        return review;
    }

    private Product getProduct() {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Teste Produtos");
        product.setDescription("Teste Description");
        return product;
    }

}
