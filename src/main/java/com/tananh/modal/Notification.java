package com.tananh.modal;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String message;

    @Column(name = "is_read")
    private Boolean isRead;  // Sử dụng tên khác thay vì "read"

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @ManyToOne
    @JoinColumn(name = "user_id_gui")
    private User userGui;

    @ManyToOne
    @JoinColumn(name = "user_id_nhan")
    private User userNhan;



    public enum NotificationType {
        FOLLOW, MESSAGE, LIKE, COMMENT, SYSTEM
    }
}