package com.tananh.service;

import java.util.List;

import com.tananh.exception.ChatException;
import com.tananh.exception.UserException;
import com.tananh.modal.Chat;
import com.tananh.modal.User;
import com.tananh.request.GroupChatRequest;

public interface ChatService {
	public Chat createChat(User userReq, Integer UserId2) throws UserException;
	public Chat FindChatById(Integer chatld) throws ChatException;
	public List<Chat> findAllChatByUserId (Integer userld) throws UserException;
	public Chat createGroup (GroupChatRequest req, User requerid) throws UserException;
	public Chat addUserToGroup (Integer userId, Integer chatId) throws UserException, ChatException;
	public Chat renameGroup (Integer chatid, String groupName, User requserId) throws ChatException;
	public Chat removeFromGroup (Integer chatId, Integer userld, User requser) throws UserException, ChatException;
	public void deleteChat (Integer chatid, Integer userid) throws ChatException, UserException;
	public Integer FindChatId(User userReq, User user) throws ChatException;
}
