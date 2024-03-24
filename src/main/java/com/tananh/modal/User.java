package com.tananh.modal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tananh.dto.UserDto;

import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userName;
    private String name;
    private String email;
    private String imageURL;
    private String moblie;
    private String userbio;
    private String password;
    private String gender;
    
    @Embedded
	@ElementCollection
    private Set<UserDto> follower = new HashSet<UserDto>();
    
    @Embedded
	@ElementCollection
    private Set<UserDto> following = new HashSet<UserDto>();
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Story> stories= new ArrayList<>();
    
    @ManyToMany
    private List<Post> savedPost= new ArrayList<>();

    
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(Integer id, String userName, String name, String email, String imageURL, String moblie, String userbio,
			String password, String gender, Set<UserDto> follower, Set<UserDto> following, List<Story> stories,
			List<Post> savedPost) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.imageURL = imageURL;
		this.moblie = moblie;
		this.userbio = userbio;
		this.password = password;
		this.gender = gender;
		this.follower = follower;
		this.following = following;
		this.stories = stories;
		this.savedPost = savedPost;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public String getUserbio() {
		return userbio;
	}


	public void setUserbio(String userbio) {
		this.userbio = userbio;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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
