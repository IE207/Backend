package com.tananh.service;

import java.util.List;
import java.util.Optional;

import com.tananh.modal.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tananh.config.JwtProvider;
import com.tananh.dto.UserDto;
import com.tananh.exception.UserException;
import com.tananh.modal.User;
import com.tananh.responsitory.UserResponsitory;

@Service
public class UserServiceImplement implements userService {

	@Autowired UserResponsitory userResponsitory;
	@Autowired JwtProvider jwtProvider;
	@Override
	public User findUserById(Integer id) throws UserException {
		Optional<User> user= userResponsitory.findById(id);
		if(user.isPresent())
		{
			return user.get();
		} throw new UserException("Không tìm  thấy người dùng với id:"+id);
	
	}

	@Override
	public List<User> findAll() {
        return userResponsitory.findAll();
	}

	@Override
	public User findUserByJWT(String jwt) throws UserException {
		String email = jwtProvider.getEmailFromToken(jwt);
		System.out.println("====================="+email);
		User user = userResponsitory.findByEmail(email);
		if(user!=null)
		{
			return user;
		}
		throw new UserException("Không tìm thấy người dùng với jwt- "+jwt);
	}

	@Override
	public List<User> findUserByIds(List<Integer> UserIds) throws UserException {
		List<User> userList = userResponsitory.findAllUserByUserIds(UserIds);
		
		return userList;
	}

	@Override
	public String Follower(String jwt,Integer followerUserId) throws UserException {
		User reqUser = findUserByJWT( jwt);
		Optional<User> followerUserOptional = userResponsitory.findById(followerUserId);

		if (followerUserOptional.isPresent()) {
			User followerUser = followerUserOptional.get();
			String result ;

			if (!isFollowing(jwt,followerUserId)){
				reqUser.getFollowing().add(followerUser);
				followerUser.getFollower().add(reqUser);
				result =  "Bạn đã theo dõi " + followerUser.getUserName();

			}
			else {
				reqUser.getFollowing().remove(followerUser);
				followerUser.getFollower().remove(reqUser);
				result =  "Bạn đã bỏ theo dõi " + followerUser.getUserName();

			}
			// Save both users
			userResponsitory.save(reqUser);
			userResponsitory.save(followerUser);
			return result;
		} else {
			throw new UserException("Không tìm thấy người dùng");
		}
	}
	@Override
	public boolean isFollowing(String jwt,Integer followerUserId) throws UserException {
		User reqUser = findUserByJWT( jwt);
		Optional<User> followerUserOptional = userResponsitory.findById(followerUserId);

		if ( followerUserOptional.isPresent()) {
			User followerUser = followerUserOptional.get();
			// Kiểm tra xem reqUser có trong danh sách following của followerUser không
			return reqUser.getFollowing().contains(followerUser);
		} else {
			throw new UserException("Không tìm thấy người dùng");
		}
	}

//	@Override
//	public String unFollow(Integer reqUserId, Integer followerUserId) throws UserException {
//		Optional<User> reqUserOptional = userResponsitory.findById(reqUserId);
//		Optional<User> followerUserOptional = userResponsitory.findById(followerUserId);
//
//		if (reqUserOptional.isPresent() && followerUserOptional.isPresent()) {
//			User reqUser = reqUserOptional.get();
//			User followerUser = followerUserOptional.get();
//
//			// Remove from following
//			reqUser.getFollowing().remove(followerUser);
//			// Remove from follower
//			followerUser.getFollower().remove(reqUser);
//
//			// Save both users
//			userResponsitory.save(reqUser);
//			userResponsitory.save(followerUser);
//
//			return "Bạn đã hủy theo dõi " + followerUser.getUserName();
//		} else {
//			throw new UserException("Không tìm thấy người dùng");
//		}
//	}



	@Override
	public List<User> searchUser(String query) throws UserException {
		List<User> users = userResponsitory.searchUser(query);
		if(users.size()==0) {
			throw new UserException("không tìm thấy người dùng");
		}
		return users;
	}

	@Override
	public User updateUserDetails(User updatedUser, Integer userId) throws UserException {
		 
	    if (updatedUser == null) {
	        throw new UserException("Thông tin người dùng cập nhật không được null");
	    }

	    Optional<User> user= userResponsitory.findById(userId);
		if(user.isPresent())
		{
				user.get().setName(updatedUser.getName());
				user.get().setEmail(updatedUser.getEmail());
				user.get().setImageURL(updatedUser.getImageURL());
				user.get().setMoblie(updatedUser.getMoblie());
				user.get().setUserbio(updatedUser.getUserbio());
				user.get().setGender(updatedUser.getGender());
				return userResponsitory.save(user.get());
		} throw new UserException("Không tìm  thấy người dùng với id:"+userId);
	

	  
	   

	   
	    
	}

	@Override
	public User findUserByUsername(String name) throws UserException {
		Optional<User> user = userResponsitory.findByUserName(name);
		if(user.isPresent())
		{
			return user.get();
		}throw new UserException("Không tìm  thấy người dùng với tên: "+name);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userResponsitory.findByEmail(email);
	}

}
