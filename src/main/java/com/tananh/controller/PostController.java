package com.tananh.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@GetMapping("/all")
	public ResponseEntity<List<Post>> findAllPostHandler() throws PostException,UserException{
		
		List<Post> posts = postService.findALlPost();
		
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	@GetMapping("/all/mypost")
	public ResponseEntity<List<Post>> findPostByUserIdHandler(@RequestHeader("Authorization") String jwt) throws PostException,UserException{
		User user = userService.findUserByJWT(jwt);
		List<Post> posts = postService.findAllPostByUserId(user.getId());
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePostHandler(@PathVariable("id") Integer postId, @RequestHeader("Authorization") String jwt) throws PostException,UserException{
		User user = userService.findUserByJWT(jwt);
		Integer userId= user.getId();
		String deletedSuccess = postService.deletePost(postId, userId);
		
		return new ResponseEntity<String>(deletedSuccess,HttpStatus.OK);
	}
	
//	@GetMapping("/all/{id}")
//	public ResponseEntity<List<Post>> findPostByUserIdHandler(@PathVariable("id") Integer userId) throws PostException,UserException{
//		List<Post> posts = postService.findPostByUserId(userId);
//		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
//	}
	
	@GetMapping("/following/{ids}")
	public ResponseEntity<List<Post>> findAllPostByUserIdHandler(@PathVariable("ids") List<Integer> userId) throws PostException,UserException{
		List<Post> posts = postService.findAllPostByUserIds(userId);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws PostException,UserException{
		Post posts = postService.findPostById(postId);
		return new ResponseEntity<Post>(posts,HttpStatus.OK);
	}
	
	@PutMapping("/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws PostException,UserException{
		
		User user = userService.findUserByJWT(jwt);
		Integer userId= user.getId();
		Post post = postService.likePost(userId, postId);
		
		return new ResponseEntity<Post>(post,HttpStatus.OK);
	}
	
	@PutMapping("/unlike/{postId}")
	public ResponseEntity<Post> unlikePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws PostException,UserException{
		
		User user = userService.findUserByJWT(jwt);
		Integer userId= user.getId();
		Post post = postService.unLikePost(userId, postId);
		
		return new ResponseEntity<Post>(post,HttpStatus.OK);
	}
	
	@PutMapping("/save/{postId}")
	public ResponseEntity<String> savePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws PostException,UserException{
		User user = userService.findUserByJWT(jwt);
		Integer userId= user.getId();
		String savedSuccess = postService.savePost(userId, postId);
		
		return new ResponseEntity<String>(savedSuccess,HttpStatus.ACCEPTED);
	}
	@PutMapping("/unsave/{postId}")
	public ResponseEntity<String> unsavePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws PostException,UserException{
		User user = userService.findUserByJWT(jwt);
		Integer userId= user.getId();
		String savedSuccess = postService.unSavePost(userId, postId);
		
		return new ResponseEntity<String>(savedSuccess,HttpStatus.ACCEPTED);
	}
}
