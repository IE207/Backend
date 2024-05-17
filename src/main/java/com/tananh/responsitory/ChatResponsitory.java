package com.tananh.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tananh.modal.Chat;
import com.tananh.modal.User;

@Repository
public interface ChatResponsitory extends JpaRepository<Chat,Integer>{
	
	 @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id = :userId")
	  List<Chat> findAllChatByUserId(@Param("userId") Integer userId);
	
	@Query("SELECT c FROM Chat c WHERE c.isGroup = false AND :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    Chat findSingleChatByUserIds(@Param("user") User user, @Param("reqUser") User reqUser);
}
