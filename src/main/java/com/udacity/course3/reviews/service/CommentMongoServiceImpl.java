package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.domain.comment.Comment;
import com.udacity.course3.reviews.domain.comment.CommentDocument;
import com.udacity.course3.reviews.repository.CommentsMongoRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentMongoServiceImpl implements CommentMongoService{

	private final CommentsMongoRepository commentsMongoRepository;

	public CommentMongoServiceImpl(CommentsMongoRepository commentsMongoRepository) {
		this.commentsMongoRepository = commentsMongoRepository;
	}

	@Override
	public void saveComment(Comment jpaComment) {
		CommentDocument commentDocument = new CommentDocument(jpaComment);
		this.commentsMongoRepository.save(commentDocument);
	}
}
