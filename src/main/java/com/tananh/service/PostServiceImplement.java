package com.tananh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tananh.dto.UserDto;
import com.tananh.exception.PostException;
import com.tananh.exception.UserException;
import com.tananh.modal.Post;
import com.tananh.modal.User;
import com.tananh.responsitory.PostResponsitory;
import com.tananh.responsitory.UserResponsitory;

public class PostServiceImplement implements PostService{

	@Autowired userService userService;
	@Autowired PostResponsitory postResponsitory;
	@Autowired UserResponsitory  userResponsitory;
	
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
	public Post updatePost(Post postUpdate, Integer userId) throws PostException {
			if(postUpdate==null) {
				throw new PostException("Thông tin cập nhật không có!");
			}
			Post post = findPostById(postUpdate.getId());
			if(postUpdate.getUser().getUserId().equals(userId)) 
			{
				post.setCaption(postUpdate.getCaption());
				post.setCreateAt(postUpdate.getCreateAt());
				post.setImage(postUpdate.getImage());
				
				return postResponsitory.save(post);
			}
			throw new PostException("Không thể sửa khi không phải là người tạo Post");
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws PostException, UserException {
//		User user = userService.findUserById(userId);
		
		Post post = findPostById(postId);
		if(post.getUser().getUserId().equals(userId)) {
			 postResponsitory.deleteById(postId);
			 return "Đã xóa thành công";
		}
		throw new PostException("Không thể xóa bài viết của người khác");
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws PostException, UserException {
		List<Post> posts= postResponsitory.findPostByUserid(userId);
		if(posts.size()>0)
		{	
			return posts;
		}
		throw new PostException("không tìm thấy post nào cả");
	}

	@Override
	public Post findPostById(Integer postId) throws PostException {
		Optional<Post> post = postResponsitory.findById(postId);
		if(post.isPresent())
		{
			
			return post.get();
		}
		throw new PostException("Không tìm thấy post với id: "+postId);
	}

	@Override
	public String savePost(Integer userId, Integer postId) throws PostException, UserException {
		User user = userService.findUserById(userId);
		
		Post post = findPostById(postId);
		
		if(!user.getSavedPost().contains(post))
		{
			user.getSavedPost().add(post);
			userResponsitory.save(user);
		}
		return "Post đã được lưu thành công";
	}

	@Override
	public String unSavePost(Integer userId, Integer postId) throws PostException, UserException  {
		User user = userService.findUserById(userId);
		
		Post post = findPostById(postId);
		
		if(user.getSavedPost().contains(post))
		{
			user.getSavedPost().remove(post);
			userResponsitory.save(user);
		}
		return "Post đã được xóa lưu thành công";
	}

	@Override
	public Post likePost(Integer userId, Integer postId) throws PostException , UserException{
		User user = userService.findUserById(userId);
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setUserName(user.getUserName());
		Post post = findPostById(postId);
		post.getLikeByUser().add(userDto);
		
		postResponsitory.save(post);
		return postResponsitory.save(post);
	}

	@Override
	public Post unLikePost(Integer userId, Integer postId) throws PostException, UserException {
		User user = userService.findUserById(userId);
		UserDto userDto = new UserDto();
		userDto.setUserId(userId);
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setUserName(user.getUserName());
		Post post = findPostById(postId);
	
		post.getLikeByUser().remove(userDto);
		
		postResponsitory.save(post);
		return postResponsitory.save(post);
	}

	@Override
	public List<Post> findAllPostByUserId(List<Integer> userIds) throws PostException, UserException {
		List<Post> posts= postResponsitory.findAllPostByUserIds(userIds);
		if(posts.size()>0)
		{
			return posts;
		}
		throw new PostException("Không tìm thấy bài viết");
	}

	@Override
	public List<Post> searchPosts(String query) throws PostException {
		List<Post> posts= postResponsitory.searchPost(query);
		if(posts.size()>0)
		{
			return posts;
		}
		throw new PostException("Không tìm thấy bài viết nào");
	}

}
