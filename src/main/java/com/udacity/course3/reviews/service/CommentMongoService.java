package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.domain.comment.Comment;

public interface CommentMongoService {

	public void saveComment(Comment jpaComment);
}
