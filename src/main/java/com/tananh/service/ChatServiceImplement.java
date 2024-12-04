package com.tananh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tananh.exception.ChatException;
import com.tananh.exception.UserException;
import com.tananh.modal.Chat;
import com.tananh.modal.User;
import com.tananh.request.GroupChatRequest;
import com.tananh.responsitory.ChatResponsitory;

@Service
public class ChatServiceImplement implements ChatService{

	@Autowired ChatResponsitory chatResponsitory;
	@Autowired userService userService;
	
	@Override
	public Chat createChat(User userReq, Integer UserId2) throws UserException {
		User user= userService.findUserById(UserId2);
		Chat chat = chatResponsitory.findSingleChatByUserIds(userReq, user);
		if(chat!=null) {
			return chat;
		}
		Chat chatCreate = new Chat();
		chatCreate.setCreatBy(userReq);
		chatCreate.getUsers().add(user);
		chatCreate.getUsers().add(userReq);
		chatCreate.setIsGroup(false);
		Chat chatSaved=chatResponsitory.save(chatCreate);
		return chatSaved;
	}

	@Override
	public Chat FindChatById(Integer chatld) throws ChatException {
		Optional<Chat> chat = chatResponsitory.findById(chatld);
		if(chat.isPresent()) {
			return chat.get();
		}
		throw new ChatException("Không tìm thấy chat với id: "+chatld);
	}

	@Override
	public List<Chat> findAllChatByUserId(Integer userld) throws UserException {
		User user = userService.findUserById(userld);
		List<Chat> chatList= chatResponsitory.findAllChatByUserId(user.getId());
		
		return chatList;
	}

	@Override
	public Chat createGroup(GroupChatRequest req, User requerid) throws UserException {
		Chat group = new Chat();
		group.setChat_image(req.getChat_image());
		group.setChat_name(req.getChat_name());
		group.setCreatBy(requerid);
		for(Integer userId : req.getUserIds()) {
			User user = userService.findUserById(userId);
			group.getUsers().add(user);
		}
		Chat chatCreated = chatResponsitory.save(group);
		return chatCreated;
	}

	@Override
	public Chat addUserToGroup(Integer userId, Integer chatId) throws UserException, ChatException {
		Optional<Chat> chatOptional = chatResponsitory.findById(chatId);

        
        User user = userService.findUserById(userId);

        if (chatOptional.isPresent()) {
            Chat chat = chatOptional.get();
            
           
            if (chat.getUsers().contains(user)) {
                throw new ChatException("User đã tồn tại trong đoạn chat");
            }
            chat.getUsers().add(user);
            Chat chatSaved = chatResponsitory.save(chat);
            return chatSaved;
        } else {
            throw new ChatException("Không tìm thấy đoạn chat với ID: " + chatId);
        }
	}

	@Override
	public Chat renameGroup(Integer chatId, String groupName, User reqUser) throws ChatException {
		Optional<Chat> chatOptional = chatResponsitory.findById(chatId);

        if (chatOptional.isPresent()) {
            Chat chat = chatOptional.get();

            // Kiểm tra xem user có trong nhóm chat không
            if (chat.getUsers().contains(reqUser)) {
                // Đổi tên nhóm chat
                chat.setChat_name(groupName);
                
                // Lưu thay đổi vào cơ sở dữ liệu
                Chat chatUpdated = chatResponsitory.save(chat);
                return chatUpdated;
            } else {
                throw new ChatException("Bạn không phải là thành viên trong đoạn chat");
            }
        } else {
            throw new ChatException("Không tìm thấy đoạn chat với ID: " + chatId);
        }
        
	}

	@Override
	public Chat removeFromGroup(Integer chatId, Integer userld, User reqUser) throws UserException, ChatException {
		Optional<Chat> chatOptional = chatResponsitory.findById(chatId);
		User user = userService.findUserById(userld);
        if (chatOptional.isPresent()) {
            Chat chat = chatOptional.get();

            
            if (chat.getUsers().contains(reqUser)) {
             
                chat.getUsers().remove(user);
                
               
                Chat chatUpdated = chatResponsitory.save(chat);
                return chatUpdated;
            } else {
                throw new ChatException("Bạn không phải là thành viên trong đoạn chat");
            }
        } else {
            throw new ChatException("Không tìm thấy đoạn chat với ID: " + chatId);
        }
	}

	@Override
	public void deleteChat(Integer chatId, Integer userId) throws ChatException, UserException {
		// TODO Auto-generated method stub
			User user = userService.findUserById(userId);
        
        // Tìm kiếm chat theo ID
        Optional<Chat> chatOptional = chatResponsitory.findById(chatId);

        if (chatOptional.isPresent()) {
            Chat chat = chatOptional.get();

            // Kiểm tra xem user có trong nhóm chat không
            if (chat.getUsers().contains(user)) {
                // Xóa đoạn chat
            	chatResponsitory.deleteById(chat.getId());
            } else {
                throw new ChatException("Bạn không phải là thành viên trong đoạn chat");
            }
        } else {
            throw new ChatException("Không tìm thấy đoạn chat với ID: " + chatId);
        }
	}

	@Override
	public Integer FindChatId(User userReq, User user) throws ChatException {
		Chat chat = chatResponsitory.findSingleChatByUserIds(userReq, user);
		if(chat!=null) {
			return chat.getId();
		}
		return 0;
	}

}
