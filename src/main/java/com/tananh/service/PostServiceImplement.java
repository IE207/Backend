package com.tananh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tananh.dto.UserDto;
import com.tananh.exception.PostException;
import com.tananh.exception.UserException;
import com.tananh.modal.Post;
import com.tananh.modal.User;
import com.tananh.responsitory.PostResponsitory;

public class PostServiceImplement implements PostService{

	@Autowired userService userService;
	@Autowired PostResponsitory postResponsitory;
	
	@Override
	public Post createPost(Post post, Integer userId) throws UserException {
		User user = userService.findUserById(userId);

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setUserId(user.getId());
        userDto.setName(user.getName());
        userDto.setImageURL(user.getImageURL());
        userDto.setUserName(user.getUserName());
        post.setUser(userDto);
        Post savedPost = postResponsitory.save(post);
		return savedPost;
	}

	@Override
	public Post updatePost(Post postUpdate, Integer postId) throws PostException {
			if(postUpdate==null) {
				throw new PostException("Thông tin cập nhật NULL");
			}
			Post post = findPostById(postId);
			post.setCaption(postUpdate.getCaption());
			post.setCreateAt(postUpdate.getCreateAt());
			post.setImage(postUpdate.getImage());
			
			return postResponsitory.save(post);
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
