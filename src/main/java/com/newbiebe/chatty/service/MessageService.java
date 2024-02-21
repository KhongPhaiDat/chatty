package com.newbiebe.chatty.service;

import com.newbiebe.chatty.entity.Message;

import java.util.List;

public interface MessageService {
    Message saveMessage(Message message);

    List<Message> getAllMessages();

    Message getMessageById(Long id);

    Message getMessageByUser(String name);

    Message updateMessage(Long id, Message message);

    boolean deleteMessage(Long id);
}
