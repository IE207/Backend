package com.tananh.controller;

import com.tananh.dto.NotificationDto;
import com.tananh.modal.Notification;
import com.tananh.modal.User;
import com.tananh.response.ApiResponse;
import com.tananh.service.NotificationService;
import com.tananh.service.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserServiceImplement userService;

    // Tạo thông báo mới
    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDto notificationDto,
                                                           @RequestHeader("Authorization") String jwt)
            throws Exception {
        User reqUser = userService.findUserByJWT(jwt);
        notificationDto.setUserIdGui(reqUser.getId());
        Notification createdNotification = notificationService.createNotification(notificationDto);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    // Lấy tất cả thông báo của người dùng
    @GetMapping("")
    public ResponseEntity<List<Notification>> getNotificationsByUserId( @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJWT(jwt);
        List<Notification> notifications = notificationService.getNotifications(Long.valueOf(user.getId()));
        return ResponseEntity.ok(notifications);
    }

    // Lấy thông báo chưa đọc của người dùng
    @GetMapping("/unread/{userId}")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable Long userId,
                                                                     @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJWT(jwt);
        List<Notification> unreadNotifications = notificationService.getUnreadNotifications(userId);
        return ResponseEntity.ok(unreadNotifications);
    }

    // Đánh dấu thông báo là đã đọc
    @PutMapping("/read/{notificationId}")
    public ResponseEntity<ApiResponse> markAsRead(@PathVariable Long notificationId,
                                                  @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJWT(jwt);
        notificationService.markAsRead(notificationId);
        ApiResponse apiResponse = new ApiResponse("Notification marked as read", jwt);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Xóa thông báo
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<ApiResponse> deleteNotification(@PathVariable Long notificationId,
                                                          @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJWT(jwt);
        notificationService.deleteNotification(notificationId);
        ApiResponse apiResponse = new ApiResponse("Notification deleted successfully", jwt);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
