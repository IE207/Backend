package com.tananh.responsitory;

import com.tananh.modal.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Lấy thông báo chưa đọc của người dùng
    public List<Notification> findByUserNhan_IdAndIsReadFalse(Long userId);

    // Lấy tất cả thông báo của người dùng
    List<Notification> findByUserNhan_Id(Long userId);

    // Xóa thông báo theo ID
    void deleteById(Long id);
}
