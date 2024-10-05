package com.tananh.dto;

import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
	public UserDto(Integer id, String userName, String name, String email, String imageURL) {
		super();
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.imageURL = imageURL;
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
    

    
}
