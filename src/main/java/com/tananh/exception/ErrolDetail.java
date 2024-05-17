package com.tananh.exception;

import java.time.LocalDateTime;

public class ErrolDetail {
	private String message;
	private String error;
	private LocalDateTime timeStamp;
	
	
	
	public ErrolDetail() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ErrolDetail(String message, String error, LocalDateTime timeStamp) {
		super();
		this.message = message;
		this.error = error;
		this.timeStamp = timeStamp;
	}
	
}
