package com.tananh.service;

import java.util.List;
import java.util.Optional;

import com.tananh.exception.UserException;
import com.tananh.modal.User;

public interface userService {
	public User findUserById(Integer id)throws UserException;
	public User findUserByUsername(String name)throws UserException;
	public User findByEmail(String email);
	public User findUserByJWT(String jwt)throws UserException ;
	public List<User> findUserByIds( List<Integer> UserIds ) throws UserException;
	public String Follower(Integer reqUserId, Integer followerUserId)throws UserException;
	public String unFollow(Integer reqUserId, Integer followerUserId)throws UserException;
	public List<User> searchUser(String query)throws UserException;
	public User updateUserDetails(User updatedUser, Integer userId) throws UserException;
}
