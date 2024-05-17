package com.tananh.response;

public class ApiResponse {
	private String message;
	private String jwt;
	public ApiResponse(String message, String jwt) {
		super();
		this.message = message;
		this.jwt = jwt;
	}
	public ApiResponse() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
}
