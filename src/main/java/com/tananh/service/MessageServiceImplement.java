package com.tananh.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tananh.exception.ChatException;
import com.tananh.exception.MessageException;
import com.tananh.exception.UserException;
import com.tananh.modal.Chat;
import com.tananh.modal.Message;
import com.tananh.modal.User;
import com.tananh.request.SendMessageRequest;
import com.tananh.responsitory.ChatResponsitory;
import com.tananh.responsitory.MessageResponsitory;
import com.tananh.responsitory.UserResponsitory;

@Service
public class MessageServiceImplement implements MessageSevice{

	@Autowired 
	MessageResponsitory messageResponsitory;
	
	@Autowired
    private ChatService chatService;

    @Autowired
    private userService userService;
	@Override
	public Message SendMessage(SendMessageRequest request) throws UserException, ChatException {
		User user = userService.findUserById(request.getUserId());
        Chat chat = chatService.FindChatById(request.getChatId());
  

        Message message = new Message();
        message.setContent(request.getContent());
        message.setUser(user);
        message.setChat(chat);
        message.setTimestamp(LocalDateTime.now());

        return messageResponsitory.save(message);
	}

	@Override
	public List<Message> findAllMessageByChatId(Integer chatId,User user) throws ChatException {
		Chat chat = chatService.FindChatById(chatId);
		if(chat.getUsers().contains(user)) {
			
			List<Message> messages = messageResponsitory.findAllMessageByChatId(chat.getId());
			return messages;
		}
		throw new ChatException("Không thể xóa đoạn chat của người khác");
	}

	@Override
	public Message FindMessageById(Integer messageId) throws MessageException {
		 Optional<Message> messageOpt = messageResponsitory.findById(messageId);
	        if (!messageOpt.isPresent()) {
	            throw new MessageException("Message không tồn tại với ID: " + messageId);
	        }

	        return messageOpt.get();
		
	}

	@Override
	public void DeleteMessage(Integer messageId, User user) throws MessageException {
		Message message = FindMessageById(messageId);
        
        if(message.getUser().getId().equals(user.getId())) {
        	 messageResponsitory.deleteById(messageId);
        	
        }
        throw new MessageException("Không thể xóa tin nhắn của người khác");
		
	}

}
