package com.newbiebe.chatty.service;

import com.newbiebe.chatty.entity.Message;

import java.util.List;

public interface MessageService {
    Message saveMessage(Message message);

    List<Message> getAllMessages();

//    List<Message> getAllMesagesWithUserName();

    Message getMessageById(Long id);

    List<Message> getMessageByUserId(Long userId);

    Message updateMessage(Long id, Message message);

    boolean deleteMessage(Long id);
}
