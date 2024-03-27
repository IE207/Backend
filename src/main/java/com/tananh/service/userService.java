package com.tananh.service;

import com.tananh.exception.UserException;
import com.tananh.modal.User;

public interface userService {
	public User findUserById(Long id)throws UserException;
	public User findUserByJWT(String jwt)throws UserException ;
}
