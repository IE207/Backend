package com.tananh.dto;

import com.tananh.modal.Notification;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private String message;

    @Enumerated(EnumType.STRING)
    private Notification.NotificationType type;

    private boolean isRead;

    private Integer userIdGui;
    private Integer userIdNhan;

}
