package com.tananh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tananh.exception.PostException;
import com.tananh.exception.UserException;
import com.tananh.modal.Post;
import com.tananh.modal.User;
import com.tananh.service.PostService;
import com.tananh.service.userService;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired PostService postService;
	
	@Autowired userService userService;
	
	@PostMapping("/create")
	ResponseEntity<Post> CreatePostHandle(@RequestBody Post post, @RequestHeader("Authorization") String jwt) throws PostException, UserException{
		User user = userService.findUserByJWT(jwt);
		Integer userId= user.getId();
		Post postCreate = postService.createPost(post, userId);
		return new ResponseEntity<Post>(postCreate,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	ResponseEntity<Post> updatePostHandler(@RequestBody Post postUpdate, @RequestHeader("Authorization") String jwt)throws PostException, UserException{
		
			User user = userService.findUserByJWT(jwt);
			Integer userId= user.getId();
			if(userId==null)
			{
				throw new UserException("userID rỗng");
			}
			Post postUpdated= postService.updatePost(postUpdate, userId);
			return new ResponseEntity<Post>(postUpdated,HttpStatus.OK);
		}
	
	@GetMapping("all/{id}")
	public ResponseEntity<List<Post>> findAllPostHandler(@PathVariable("id") Integer userId) throws PostException,UserException{
		
		List<Post> posts = postService.findPostByUserId(userId);
		
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
}
