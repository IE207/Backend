package com.tananh.service;

import com.tananh.exception.UserException;
import com.tananh.modal.Post;

public interface PostService {
	public Post createPost(Post post, Integer userId) throws UserException;
	
//	public Post updatePost(Post post,)
}
	