package com.tananh.dto;

public class UserDto {
	private Integer id;
    private String userName;
    private String name;
    private String email;
    private String imageURL;
    
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(Integer userId, String userName, String name, String email, String imageURL) {
		super();
		this.id = userId;
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.imageURL = imageURL;
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
    
}
