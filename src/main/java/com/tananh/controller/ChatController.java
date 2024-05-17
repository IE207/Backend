package com.tananh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tananh.exception.ChatException;
import com.tananh.exception.UserException;
import com.tananh.modal.Chat;
import com.tananh.modal.User;
import com.tananh.request.ChatSingleRequest;
import com.tananh.request.GroupChatRequest;
import com.tananh.response.ApiResponse;
import com.tananh.service.ChatService;
import com.tananh.service.userService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
	@Autowired
    private ChatService chatService;
	
	@Autowired userService userService;
	
	@PostMapping("/createSingle")
    public ResponseEntity<Chat> createChatHandler(@RequestBody ChatSingleRequest request,@RequestHeader("Authorization") String jwt) throws UserException {
       User reqUser = userService.findUserByJWT(jwt);
       Chat chat = chatService.createChat(reqUser, request.getUserId());
       return new ResponseEntity<Chat>(chat,HttpStatus.OK);
        
    }
	@PostMapping("/createGroup")
    public ResponseEntity<Chat> createGroupChatHandler(@RequestBody GroupChatRequest request,@RequestHeader("Authorization") String jwt) throws UserException {
       User reqUser = userService.findUserByJWT(jwt);
       Chat chat = chatService.createGroup(request, reqUser);
       return new ResponseEntity<Chat>(chat,HttpStatus.OK);
        
    }
	
	@GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable Integer chatId,@RequestHeader("Authorization") String jwt) throws ChatException {
        
      Chat chat = chatService.FindChatById(chatId);
      return new ResponseEntity<Chat>(chat,HttpStatus.OK);
       
    }
	
	@GetMapping("/user")
    public ResponseEntity<List<Chat>> findChatByIdHandler(@RequestHeader("Authorization") String jwt) throws ChatException, UserException {
		User reqUser = userService.findUserByJWT(jwt);
		List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());
        return new ResponseEntity<List<Chat>>(chats,HttpStatus.OK);
       
    }
	
	@PostMapping("/{chatId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupChatHandler(@PathVariable Integer chatId,@PathVariable Integer userId,@RequestHeader("Authorization") String jwt) throws UserException, ChatException {
       Chat chat = chatService.addUserToGroup(userId, chatId);
       return new ResponseEntity<Chat>(chat,HttpStatus.OK);
        
    }
	
	@PutMapping("/{chatId}/add/{userId}")
    public ResponseEntity<Chat> removeUserFromGroupChatHandler(@PathVariable Integer chatId,@PathVariable Integer userId,@RequestHeader("Authorization") String jwt) throws UserException, ChatException {
		User reqUser = userService.findUserByJWT(jwt);
		Chat chat = chatService.removeFromGroup(chatId, userId, reqUser);
       return new ResponseEntity<Chat>(chat,HttpStatus.OK);
        
    }
	
	@DeleteMapping("/delete/{chatId}")
    public ResponseEntity<ApiResponse> deleteGroupChatHandler(@PathVariable Integer chatId,@RequestHeader("Authorization") String jwt) throws UserException, ChatException {
		User reqUser = userService.findUserByJWT(jwt);
		chatService.deleteChat(chatId, reqUser.getId());
		ApiResponse apiResponse= new ApiResponse("đã xóa thành công đoạn chat",jwt);
       return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}     
}
