package com.tananh.responsitory;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tananh.modal.User;

@Repository
public interface UserResponsitory extends JpaRepository<User, Integer>{


	
//	public Optional<User> findByUsername(String userName);
	
	 public User findByEmail(String email);
	    
	@Query("SELECT DISTINCT u FROM User u WHERE u.userName LIKE %:query% OR u.email LIKE %:query%")
	public List<User> searchUser(@Param("query") String query);
	    
	@Query("SELECT u FROM User u WHERE u.id IN :users")
	public List<User> findAllUserByUserIds(@Param("users") List<Integer> users);


}
