package com.tananh.modal;

import java.time.LocalDateTime;

import com.tananh.dto.UserDto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="story")
public class Story {
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
	private LocalDateTime timestamps;
	public Story() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Story(Integer id, UserDto user, @NotNull String image, String caption, LocalDateTime timestamps) {
		super();
		Id = id;
		this.user = user;
		this.image = image;
		this.caption = caption;
		this.timestamps = timestamps;
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
	public LocalDateTime getTimestamps() {
		return timestamps;
	}
	public void setTimestamps(LocalDateTime timestamps) {
		this.timestamps = timestamps;
	}
	
}
