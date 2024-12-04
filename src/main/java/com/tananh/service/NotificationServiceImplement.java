package com.tananh.service;

import com.tananh.dto.NotificationDto;
import com.tananh.exception.UserException;
import com.tananh.modal.Notification;
import com.tananh.responsitory.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImplement implements NotificationService {

    @Autowired
    private final NotificationRepository notificationRepository;
    private final userService userService;

    @Override
    public Notification createNotification(NotificationDto notificationDto) throws UserException {
        // Tạo thông báo từ NotificationDto
        Notification notification = Notification.builder()
                .userGui(userService.findUserById(notificationDto.getUserIdGui()))
                .userNhan(userService.findUserById(notificationDto.getUserIdNhan()))
                .message(notificationDto.getMessage())
                .type(notificationDto.getType())
                .isRead(false) // Mặc định là chưa đọc
                .build();

        return notificationRepository.save(notification);
    }

    // Lấy tất cả thông báo của người dùng
    @Override
    public List<Notification> getNotifications(Long userId) {
        return notificationRepository.findByUserNhan_Id(userId);
    }

    // Lấy thông báo chưa đọc của người dùng
    @Override
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserNhan_IdAndIsReadFalse(userId);
    }

    // Đánh dấu thông báo là đã đọc
    @Override
    public void markAsRead(Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        notification.ifPresent(n -> {
            n.setIsRead(true); // Đánh dấu là đã đọc
            notificationRepository.save(n); // Lưu thông báo đã thay đổi
        });
    }

    // Xóa thông báo
    @Override
    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }
}
