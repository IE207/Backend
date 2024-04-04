package com.tananh.service;

import java.util.List;

import com.tananh.exception.PostException;
import com.tananh.exception.UserException;
import com.tananh.modal.Post;

public class PostServiceImplement implements PostService{

	@Override
	public Post createPost(Post post, Integer userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post updatePost(Post postUpdate, Integer postId) throws PostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePost(Integer postId) throws PostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws PostException, UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post findPostById(Integer postId) throws PostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String savePost(Integer userId, Integer postId) throws PostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unSavePost(Integer userId, Integer postId) throws PostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String likePost(Integer userId, Integer postId) throws PostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unLikePost(Integer userId, Integer postId) throws PostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> findAllPostByUserId(List<Integer> userIds) throws PostException, UserException {
		// TODO Auto-generated method stub
		return null;
	}

}
