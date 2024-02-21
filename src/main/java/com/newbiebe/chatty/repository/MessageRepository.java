package com.newbiebe.chatty.repository;

import com.newbiebe.chatty.entity.ChatUser;
import com.newbiebe.chatty.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findByUserId(Long userId);

    @Query("SELECT m FROM Message m JOIN ChatUser cu ON cu.userId = m.userId")
    List<Message> getAllMessageWithUserName();

}