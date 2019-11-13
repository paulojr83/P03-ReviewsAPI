package com.udacity.course3.reviews.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.course3.reviews.controller.ProductsController;
import com.udacity.course3.reviews.domain.comment.Comment;
import com.udacity.course3.reviews.domain.product.Product;
import com.udacity.course3.reviews.domain.product.ProductRepository;
import com.udacity.course3.reviews.domain.review.Review;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProductApiApplicationTests {

    @Autowired
    private MockMvc mvc;

    private JacksonTester<Product> json;

    @MockBean
    private ProductRepository productRepository;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        Product product = getProduct();
        product.setProductId(1L);
        given(productRepository.save(any())).willReturn(product);
        given(productRepository.findById(product.getProductId())).willReturn(Optional.of(product));
        given(productRepository.findAll()).willReturn(Collections.singletonList(product));

    }


    @Test
    public void createProduct() throws Exception {
        Product product = getProduct();
        mvc.perform(
                post(new URI("/products"))
                        .content(json.write(product).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }


    @Test
    public void listProducts() throws Exception {
        Product product = getProduct();
        mvc.perform(
                get(new URI("/products"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(result -> hasSize(1))
                .andExpect(status().isOk());
    }


    @Test
    public void findById() throws Exception {
        mvc.perform(
                get(new URI("/products/1"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("product_id", is(1)))
                .andExpect(status().isOk());
    }

   private Product getProduct() {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Teste Produtos");
        List<Review> reviews = new ArrayList<>();
        Review review = new Review();

        review.setReviewId(1L);
        review.setScore(4);

        Comment comment = new Comment();

        comment.setComment("Teste esse produtos");
        comment.setCommentId(1L);
        reviews.add(review);

        return product;
    }

}
