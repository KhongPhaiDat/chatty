package com.newbiebe.chatty.service;

import com.newbiebe.chatty.entity.ChatUser;

import java.util.List;

public interface ChatUserService {
    ChatUser saveChatUser(ChatUser chatUser);

    List<ChatUser> getAllChatUsers();

    //
    Object getChatUserById(Long id);

    ChatUser updateChatUser(Long id, ChatUser chatUser);
//
//    void deleteChatUser(Long id);
}
