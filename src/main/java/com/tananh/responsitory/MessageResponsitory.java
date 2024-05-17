package com.tananh.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.tananh.modal.Message;

public interface MessageResponsitory extends JpaRepository<Message, Integer>{
	@Query("SELECT m FROM Message m JOIN m.chat c WHERE c.id = :chatId")
    List<Message> findAllMessageByChatId(@Param("chatId") Integer chatId);
}
