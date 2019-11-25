package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.domain.comment.CommentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentsMongoRepository extends MongoRepository<CommentDocument, String> {
}
