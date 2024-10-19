package com.tananh.service;

import java.util.List;

import com.tananh.exception.PostException;
import com.tananh.exception.UserException;
import com.tananh.modal.Post;

public interface PostService {
	public Post createPost(Post post, Integer userId) throws UserException;
	
	public Post updatePost(Post postUpdate, Integer userId) throws PostException;
	
	public String deletePost(Integer postId, Integer userId) throws PostException, UserException;
	
	public List<Post> findALlPost();

	public List<Post> findAllPostByUserId(Integer userId) throws UserException;
	public List<Post> findAllPostByUserIds(List<Integer> userIds) throws PostException,UserException;
	
	public Post findPostById(Integer postId) throws PostException;
	
	public String savePost (Integer userId, Integer postId) throws PostException, UserException;
	
	public String unSavePost (Integer userId, Integer postId) throws PostException, UserException ;
	
	public Post likePost (Integer userId, Integer postId) throws PostException, UserException;
	
	public Post unLikePost (Integer userId, Integer postId) throws PostException, UserException;
	
	public List<Post> searchPosts (String query) throws PostException;
	boolean userLikedPost(Integer userid, Integer postid) throws PostException, UserException;
}
	