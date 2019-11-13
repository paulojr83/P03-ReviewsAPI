package com.udacity.course3.reviews.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.course3.reviews.domain.comment.Comment;
import com.udacity.course3.reviews.domain.comment.CommentRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CommentsApplicationTests {

    @Autowired
    private MockMvc mvc;

    private JacksonTester<Comment> json;

    @MockBean
    private CommentRepository commentRepository;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        Comment comment = getComment();
        comment.setCommentId(1L);
        given(commentRepository.save(any())).willReturn(comment);
        given(commentRepository.findById(comment.getCommentId())).willReturn(Optional.of(comment));
        given(commentRepository.findAll()).willReturn(Collections.singletonList(comment));
    }

    @Test
    public void createCommentForReview() throws Exception {
        Comment comment = getComment();
        mvc.perform(
                post(new URI("/reviews/{reviewId}"))
                        .content(json.write(comment).getJson())
                        .param("reviewId", "1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }


    @Test
    public void listCommentsForReview() throws Exception {
        mvc.perform(
                get(new URI("/reviews/{reviewId}"))
                        .param("reviewId", "1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(result -> hasSize(1))
                .andExpect(status().isOk());
    }

   private Comment getComment() {
       Comment comment = new Comment();
       comment.setCommentId(1L);
       comment.setComment("Teste Comment");
       comment.setTitle("Teste title");

        Review review = new Review();

        review.setReviewId(1L);
        review.setScore(4);

        comment.setReview(review);

        return comment;
    }

}
