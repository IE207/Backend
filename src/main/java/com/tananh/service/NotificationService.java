package com.tananh.service;

import com.tananh.dto.NotificationDto;
import com.tananh.exception.UserException;
import com.tananh.modal.Notification;

import java.util.List;

public interface NotificationService {


    // Tạo một thông báo mới
    public Notification createNotification(NotificationDto notificationDto) throws UserException;

    // Lấy tất cả thông báo của người dùng
    public List<Notification> getNotifications(Long userId);

    // Lấy thông báo chưa đọc của người dùng
    public List<Notification> getUnreadNotifications(Long userId);

    // Đánh dấu thông báo là đã đọc
    public void markAsRead(Long notificationId) ;

    // Xóa thông báo
    public void deleteNotification(Long notificationId) ;
}
