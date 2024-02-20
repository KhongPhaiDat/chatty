package com.newbiebe.chatty.repository;

import com.newbiebe.chatty.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}