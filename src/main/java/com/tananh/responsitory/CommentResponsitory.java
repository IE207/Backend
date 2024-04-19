package com.tananh.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tananh.modal.Comments;


@Repository
public interface CommentResponsitory extends JpaRepository<Comments, Integer>{

	@Query("SELECT c FROM Comments c WHERE c.post.id = :postId ORDER BY c.createAt DESC")
	public List<Comments> findAllCommentsByPostIds(@Param("postId") Integer postId);

}
