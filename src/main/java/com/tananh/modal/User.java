package com.tananh.modal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tananh.dto.UserDto;


import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String name;
    private String email;
    private String imageURL;
    private String moblie;
    private String userBio;
    private String passWord;
    private String gender;
    private Set<UserDto> follower = new HashSet<UserDto>();
    
    private Set<UserDto> following = new HashSet<UserDto>();
    
    private List<Story> stories= new ArrayList<>();
    
    private List<Post> savedPost= new ArrayList<>();

    
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(Integer id, String userName, String name, String email, String imageURL, String moblie, String userBio,
			String passWord, String gender, Set<UserDto> follower, Set<UserDto> following, List<Story> stories,
			List<Post> savedPost) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.imageURL = imageURL;
		this.moblie = moblie;
		this.userBio = userBio;
		this.passWord = passWord;
		this.gender = gender;
		this.follower = follower;
		this.following = following;
		this.stories = stories;
		this.savedPost = savedPost;
	}

	public Integer getUserId() {
		return id;
	}

	public void setUserId(Integer userId) {
		this.id = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public String getUserBio() {
		return userBio;
	}

	public void setUserBio(String userBio) {
		this.userBio = userBio;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<UserDto> getFollower() {
		return follower;
	}

	public void setFollower(Set<UserDto> follower) {
		this.follower = follower;
	}

	public Set<UserDto> getFollowing() {
		return following;
	}

	public void setFollowing(Set<UserDto> following) {
		this.following = following;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	public List<Post> getSavedPost() {
		return savedPost;
	}

	public void setSavedPost(List<Post> savedPost) {
		this.savedPost = savedPost;
	}
    
    
}
