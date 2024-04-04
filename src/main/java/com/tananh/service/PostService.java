package com.tananh.service;

import java.util.List;

import com.tananh.exception.PostException;
import com.tananh.exception.UserException;
import com.tananh.modal.Post;

public interface PostService {
	public Post createPost(Post post, Integer userId) throws UserException;
	
	public Post updatePost(Post postUpdate, Integer postId) throws PostException;
	
	public String deletePost(Integer postId) throws PostException;
	
	public List<Post> findPostByUserId(Integer userId) throws PostException,UserException;
	
	public List<Post> findAllPostByUserId(List<Integer> userIds) throws PostException,UserException;
	
	public Post findPostById(Integer postId) throws PostException;
	
	public String savePost (Integer userId, Integer postId) throws PostException;
	
	public String unSavePost (Integer userId, Integer postId) throws PostException;
	
	public String likePost (Integer userId, Integer postId) throws PostException;
	
	public String unLikePost (Integer userId, Integer postId) throws PostException;
}
	