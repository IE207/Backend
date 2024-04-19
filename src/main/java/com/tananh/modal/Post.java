package com.tananh.modal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tananh.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.*;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="id", column=@Column(name="user_id")),
		@AttributeOverride(name="email", column=@Column(name="user_email"))
	})
	private UserDto user;
	
	@NotNull
	private String image;
	
	private String caption;
	private LocalDateTime CreateAt;
	
	@OneToMany(mappedBy = "post")
	private List<Comments> comments = new ArrayList<>();
	
	@Embedded
	@ElementCollection
	@JoinTable(name = "likeByUsers", joinColumns = @JoinColumn(name = "user_id"))
	private Set<UserDto> likeByUsers = new HashSet<UserDto>();

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(Integer id, UserDto user, @NotNull String image, String caption, LocalDateTime createAt,
			List<Comments> comments, Set<UserDto> likeByUser) {
		super();
		Id = id;
		this.user = user;
		this.image = image;
		this.caption = caption;
		CreateAt = createAt;
		this.comments = comments;
		this.likeByUsers = likeByUser;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public LocalDateTime getCreateAt() {
		return CreateAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		CreateAt = createAt;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public Set<UserDto> getLikeByUser() {
		return likeByUsers;
	}

	public void setLikeByUser(Set<UserDto> likeByUser) {
		this.likeByUsers = likeByUser;
	}
	
	
}
