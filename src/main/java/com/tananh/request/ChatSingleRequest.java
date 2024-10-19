package com.tananh.request;

public class ChatSingleRequest {
	private Integer userId;
	private String message;
	public ChatSingleRequest(Integer userId) {
		super();
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public ChatSingleRequest() {
		super();
	}
	
	
	
}
