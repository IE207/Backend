package com.tananh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
	@Autowired
    private ChatService chatService;

	@Autowired userService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@PostMapping("/createSingle/{userId}")
    public ResponseEntity<Chat> createChatHandler(
            @PathVariable ("userId") Integer userId ,
            @RequestHeader("Authorization") String jwt) throws UserException {
       User reqUser = userService.findUserByJWT(jwt);
       Chat chat = chatService.createChat(reqUser, userId);
       return new ResponseEntity<Chat>(chat,HttpStatus.OK);

    }
    @GetMapping("/getChatId/{userId2}")
    public ResponseEntity<Integer> getChatIdByUsers(@PathVariable Integer userId2,
                                                    @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserByJWT(jwt); // Lấy người dùng hiện tại từ JWT

        // Kiểm tra xem userId1 và userId2 có thuộc về người dùng hiện tại không
        if ( reqUser.getId() == userId2) {
            throw new UserException("User is not authorized to view this chat");
        }
        User user = userService.findUserById(userId2);

        // Tìm cuộc trò chuyện giữa userId1 và userId2
        Integer chatId = chatService.FindChatId(reqUser, user);
        if (chatId == null) {
            throw new ChatException("No chat found between the users");
        }

        return new ResponseEntity<>(chatId, HttpStatus.OK);
    }
//	@PostMapping("/createGroup")
//    public ResponseEntity<Chat> createGroupChatHandler(@RequestBody GroupChatRequest request,@RequestHeader("Authorization") String jwt) throws UserException {
//       User reqUser = userService.findUserByJWT(jwt);
//       Chat chat = chatService.createGroup(request, reqUser);
//       return new ResponseEntity<Chat>(chat,HttpStatus.OK);
//
//    }

	@GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable Integer chatId,@RequestHeader("Authorization") String jwt) throws ChatException {

      Chat chat = chatService.FindChatById(chatId);
      return new ResponseEntity<Chat>(chat,HttpStatus.OK);

    }
//    @MessageMapping("/sendPrivateMessage")  // Được dùng để nhận tin nhắn từ WebSocket client
//    public void sendPrivateMessage(ChatSingleRequest request,@RequestHeader("Authorization") String jwt) throws UserException {
//        // Lấy thông tin người gửi và người nhận từ request
//        User sender = userService.findUserByJWT(jwt);
//        User receiver = userService.findUserById(request.getUserId());
//
//        // Gửi tin nhắn riêng qua WebSocket
//        messagingTemplate.convertAndSendToUser(receiver.getUserName(), "/queue/messages", "Tin nhắn từ " + sender.getUserName() + ": " + request.getMessage());
//    }

	@GetMapping("/user")
    public ResponseEntity<List<Chat>> findChatByIdHandler(@RequestHeader("Authorization") String jwt) throws ChatException, UserException {
		User reqUser = userService.findUserByJWT(jwt);
		List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());
        return new ResponseEntity<List<Chat>>(chats,HttpStatus.OK);

    }
//    @MessageMapping("/sendGroupMessage")
//    public void sendGroupMessage(GroupChatRequest request,@RequestHeader("Authorization") String jwt) throws UserException, ChatException {
//        User sender = userService.findUserByJWT(jwt);
//        Chat chat = chatService.FindChatById(request.getChatId());
//
//        // Gửi tin nhắn tới tất cả các thành viên trong group
//        messagingTemplate.convertAndSend("/topic/group/" + chat.getId(), "Tin nhắn từ " + sender.getUserName() + ": " + request.getMessage());
//    }

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
