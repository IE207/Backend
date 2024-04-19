package com.tananh.service;

import java.util.List;

import com.tananh.exception.CommentException;
import com.tananh.exception.PostException;
import com.tananh.exception.UserException;
import com.tananh.modal.Comments;

public interface CommentService {

	public Comments createComments(Comments comments, Integer userID, Integer postId) throws CommentException,UserException, PostException;
	
	public String deleteComment(Integer commentId, Integer userId) throws CommentException,UserException;
	
	public Comments updateComment(String content,Integer commentId, Integer userId) throws CommentException,UserException;
	
	public Comments likeComment (Integer commentId, Integer userId) throws CommentException,UserException;
	
	public Comments unlikeComment (Integer commentId, Integer userId) throws CommentException,UserException;
	
	public List<Comments> getALlCommentByPostId(Integer postId) throws PostException;
	
	public Comments findCommentByid(Integer commentId) throws CommentException;
	
	
	
}
