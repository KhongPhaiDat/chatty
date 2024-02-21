package com.newbiebe.chatty.repository;

import com.newbiebe.chatty.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    ChatUser findByName(String name);
    
}