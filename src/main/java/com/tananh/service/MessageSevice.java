package com.tananh.service;

import java.util.List;

import com.tananh.exception.ChatException;
import com.tananh.exception.MessageException;
import com.tananh.exception.UserException;
import com.tananh.modal.Message;
import com.tananh.modal.User;
import com.tananh.request.SendMessageRequest;

public interface MessageSevice {

	public Message SendMessage(SendMessageRequest request) throws UserException,ChatException;
	public List<Message> findAllMessageByChatId(Integer chatId, User user) throws ChatException,UserException;
	public Message FindMessageById(Integer messageId) throws MessageException;
	public void DeleteMessage(Integer messageId, User user) throws MessageException;
}
