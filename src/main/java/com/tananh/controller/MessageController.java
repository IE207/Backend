package com.tananh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tananh.exception.ChatException;
import com.tananh.exception.MessageException;
import com.tananh.exception.UserException;
import com.tananh.modal.Message;
import com.tananh.modal.User;
import com.tananh.request.SendMessageRequest;
import com.tananh.response.ApiResponse;
import com.tananh.service.MessageSevice;
import com.tananh.service.userService;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    
    @Autowired
    private MessageSevice messageService;

    @Autowired
    private userService userService;
    
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody SendMessageRequest request, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User reqUser = userService.findUserByJWT(jwt);
        request.setUserId(reqUser.getId());
        Message message = messageService.SendMessage(request);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
    
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws UserException, ChatException {
        User user = userService.findUserByJWT(jwt);
        List<Message> messages = messageService.findAllMessageByChatId(chatId, user);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) throws MessageException {
        Message message = messageService.FindMessageById(messageId);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessage(@PathVariable Integer messageId, @RequestHeader("Authorization") String jwt) throws UserException, MessageException {
        User user = userService.findUserByJWT(jwt);
        messageService.DeleteMessage(messageId, user);
        ApiResponse apiResponse = new ApiResponse("Message deleted successfully", jwt);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
