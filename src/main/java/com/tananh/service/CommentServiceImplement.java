package com.tananh.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tananh.dto.UserDto;
import com.tananh.exception.CommentException;
import com.tananh.exception.PostException;
import com.tananh.exception.UserException;
import com.tananh.modal.Comments;
import com.tananh.modal.Post;
import com.tananh.modal.User;
import com.tananh.responsitory.CommentResponsitory;
import com.tananh.responsitory.PostResponsitory;


@Service
public class CommentServiceImplement implements CommentService{

	@Autowired userService userService;
	@Autowired CommentResponsitory commentResponsitory;
	@Autowired PostService postService;
	@Autowired PostResponsitory postResponsitory;
	
	@Override
	public Comments createComments(Comments comments, Integer userID, Integer postId) throws CommentException, UserException, PostException {
	    User user = userService.findUserById(userID);
	    
	    UserDto userDto = new UserDto();
	    userDto.setId(user.getId());
	    userDto.setEmail(user.getEmail());
	    userDto.setName(user.getName());
	    userDto.setUserName(user.getUserName());
	    userDto.setImageURL(user.getImageURL());
	    
	    comments.setUserDto(userDto);
	    comments.setCreateAt(LocalDateTime.now());
	    
	    Post post = postService.findPostById(postId);
	    post.getComments().add(comments);
	    
	    // Set the post reference in the comment entity
	    comments.setPost(post);
	    
	    Comments commentSave = commentResponsitory.save(comments);
	    postResponsitory.save(post);
	    
	    return commentSave;
	}


	@Override
	public String deleteComment(Integer commentId, Integer userId) throws CommentException, UserException {
		Comments cmt = findCommentByid(commentId);
		if(cmt.getUserDto().getId().equals(userId))
		{
			commentResponsitory.delete(cmt);
			return "Đã xóa thành công";
		}
		throw new CommentException("Xóa không thành công");
	}

	@Override
	public Comments updateComment(String content,Integer commentId, Integer userId) throws CommentException, UserException {
		Comments cmt = findCommentByid(commentId);
		if(cmt.getUserDto().getId().equals(userId)) {
			cmt.setContent(content);
			cmt.setCreateAt(LocalDateTime.now());
			Comments updatedcmt=commentResponsitory.save(cmt);
			return updatedcmt;
		}
		throw new CommentException("Update không thành công");
		
		
	}

	@Override
	public Comments likeComment(Integer commentId, Integer userId) throws CommentException, UserException {
		User user = userService.findUserById(userId);
		UserDto userDto = new UserDto();
		userDto.setId(userId);
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setUserName(user.getUserName());
		
		Comments cmt = findCommentByid(commentId);
		cmt.getLikeByUser().add(userDto);
		
		return commentResponsitory.save(cmt);
	}

	@Override
	public Comments unlikeComment(Integer commentId, Integer userId) throws CommentException, UserException {
		User user = userService.findUserById(userId);
		UserDto userDto = new UserDto();
		userDto.setId(userId);
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setUserName(user.getUserName());
		
		Comments cmt = findCommentByid(commentId);
		cmt.getLikeByUser().removeIf(u -> u.getId().equals(userId));
		
		return commentResponsitory.save(cmt);
	}

	@Override
	public List<Comments> getALlCommentByPostId(Integer postId) throws PostException {
		List<Comments> cmts = commentResponsitory.findAllCommentsByPostIds(postId);
		
		return cmts;
	}

	@Override
	public Comments findCommentByid(Integer commentId) throws CommentException {
		Optional<Comments> cmt = commentResponsitory.findById(commentId);
		if(cmt.isPresent())
		{
			return cmt.get();
		}
		throw new CommentException("Không tìm thấy comment với commentId: "+commentId);
	}

}
