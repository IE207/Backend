package com.tananh.modal;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tananh.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="comments")
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="id", column=@Column(name="user_id")),
		@AttributeOverride(name="email", column=@Column(name="user_email"))
	})
	private UserDto userDto;
	
	private String content;
	
	@Embedded
	@ElementCollection
	private Set<UserDto> likeByUser =new HashSet<UserDto>();
	
	private LocalDateTime createAt;

	 @JsonIgnore	
	 @ManyToOne
	 @JoinColumn(name = "post_id")
	 private Post post;
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Comments(Integer id, UserDto userDto, String content, Set<UserDto> likeByUser, LocalDateTime createAt,
			Post post) {
		super();
		this.id = id;
		this.userDto = userDto;
		this.content = content;
		this.likeByUser = likeByUser;
		this.createAt = createAt;
		this.post = post;
	}




	public Post getPost() {
		return post;
	}




	public void setPost(Post post) {
		this.post = post;
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public UserDto getUserDto() {
		return userDto;
	}


	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Set<UserDto> getLikeByUser() {
		return likeByUser;
	}


	public void setLikeByUser(Set<UserDto> likeByUser) {
		this.likeByUser = likeByUser;
	}


	public LocalDateTime getCreateAt() {
		return createAt;
	}


	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
}
