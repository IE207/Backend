package com.tananh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.tananh.exception.CommentException;
import com.tananh.exception.PostException;
import com.tananh.exception.UserException;
import com.tananh.modal.Comments;
import com.tananh.modal.User;
import com.tananh.request.CommentsRequest;
import com.tananh.service.CommentService;
import com.tananh.service.userService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired CommentService commentService;
	@Autowired userService userService;
	
	@PostMapping("/create/{postId}")
	ResponseEntity<Comments> createCommentHandler(@PathVariable Integer postId,@RequestBody Comments comment, @RequestHeader("Authorization") String jwt)
	throws CommentException, UserException, PostException{
		
		User user = userService.findUserByJWT(jwt);
		Integer userId= user.getId();
		Comments cmt = commentService.createComments(comment, userId, postId);
		return new ResponseEntity<Comments>(cmt,HttpStatus.OK);
	}
	
	@PutMapping("/update/{commentId}")
	public ResponseEntity<Comments> updateCommentHandler(@PathVariable Integer commentId, @RequestBody Comments commentsRequest, @RequestHeader("Authorization") String jwt) throws CommentException, UserException {
	    User user = userService.findUserByJWT(jwt);
	    Integer userId = user.getId();
	    String content =commentsRequest.getContent();
	    Comments updatedComment = commentService.updateComment(content, commentId, userId);
	    return new ResponseEntity<>(updatedComment, HttpStatus.OK);
	}

	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<String> deleteCommentHandler(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt) throws CommentException, UserException {
	    User user = userService.findUserByJWT(jwt);
	    Integer userId = user.getId();
	    String message = commentService.deleteComment(commentId, userId);
	    return new ResponseEntity<>(message, HttpStatus.OK);
	}
	@PutMapping("/like/{commentId}")
	public ResponseEntity<Comments> likeCommentHandler(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt) throws CommentException, UserException {
	    User user = userService.findUserByJWT(jwt);
	    Integer userId = user.getId();
	    Comments likedComment = commentService.likeComment(commentId, userId);
	    return new ResponseEntity<>(likedComment, HttpStatus.OK);
	}
	
	@PutMapping("/unlike/{commentId}")
	public ResponseEntity<Comments> unlikeCommentHandler(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt) throws CommentException, UserException {
	    User user = userService.findUserByJWT(jwt);
	    Integer userId = user.getId();
	    Comments unlikedComment = commentService.unlikeComment(commentId, userId);
	    return new ResponseEntity<>(unlikedComment, HttpStatus.OK);
	}
	
	@GetMapping("/all/{postId}")
	public ResponseEntity<List<Comments>> getAllCommentsByPostIdHandler(@PathVariable Integer postId) throws PostException {
	    List<Comments> comments = commentService.getALlCommentByPostId(postId);
	    return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	@GetMapping("/{commentId}")
	public ResponseEntity<Comments> getCommentByIdHandler(@PathVariable Integer commentId) throws CommentException {
	    Comments comment = commentService.findCommentByid(commentId);
	    return new ResponseEntity<>(comment, HttpStatus.OK);
	}


}
