package com.tananh.modal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class Chat{


	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
    private String chat_name;
    private String chat_image;
    
    @Column(name="is_group")
    private Boolean isGroup;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="create_by")
    private User createBy;
    
    @ManyToMany
    private Set<User> users= new HashSet<>();
    
    @OneToMany
    private List<Message> messages = new ArrayList<>();

	public Chat() {
		super();
	}

	public Chat(Integer id, String chat_name, String chat_image, Boolean isGroup, User creatBy, Set<User> users,
			List<Message> messages) {
		super();
		this.id = id;
		this.chat_name = chat_name;
		this.chat_image = chat_image;
		this.isGroup = isGroup;
		this.createBy = creatBy;
		this.users = users;
		this.messages = messages;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChat_name() {
		return chat_name;
	}

	public void setChat_name(String chat_name) {
		this.chat_name = chat_name;
	}

	public String getChat_image() {
		return chat_image;
	}

	public void setChat_image(String chat_image) {
		this.chat_image = chat_image;
	}

	public Boolean getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Boolean isGroup) {
		this.isGroup = isGroup;
	}

	public User getCreatBy() {
		return createBy;
	}

	public void setCreatBy(User creatBy) {
		this.createBy = creatBy;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	
}
