package com.tananh.service;

import java.util.List;

import com.tananh.exception.UserException;
import com.tananh.modal.User;

public interface userService {
	public User findUserById(Integer id)throws UserException;
	public User findUserByJWT(String jwt)throws UserException ;
	public List<User> findUserByIds( List<Integer> UserIds ) throws UserException;
	public String Follower(Integer reqUserId, Integer followerUserId)throws UserException;
	public String unFollow(Integer reqUserId, Integer followerUserId)throws UserException;
	public List<User> searchUser(String query)throws UserException;
	public User updateUserDetails(User updatedUser, User existingUser) throws UserException;
}
