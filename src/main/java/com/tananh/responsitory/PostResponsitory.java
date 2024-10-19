package com.tananh.responsitory;

import java.util.List;
import java.util.Optional;

import com.tananh.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tananh.modal.Post;


public interface PostResponsitory extends JpaRepository<Post, Integer>{
	@Query("SELECT p FROM Post p WHERE p.caption LIKE %:query% ORDER BY p.CreateAt DESC")
	public List<Post> searchPost(@Param("query") String query);

	   
	@Query("SELECT p FROM Post p WHERE p.user.id = ?1")
	public List<Post> findPostByUserid(Integer userId);
	
	
	@Query("SELECT p FROM Post p WHERE p.user.id IN :userIds ORDER BY p.CreateAt DESC")
	public List<Post> findAllPostByUserIds(@Param("userIds") List<Integer> userIds);

}
