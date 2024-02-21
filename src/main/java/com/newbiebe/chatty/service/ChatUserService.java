package com.newbiebe.chatty.service;

import com.newbiebe.chatty.entity.ChatUser;

import java.util.List;

public interface ChatUserService {
    ChatUser saveChatUser(ChatUser chatUser);

    List<ChatUser> getAllChatUsers();

    ChatUser getChatUserById(Long id);

    ChatUser getChatUserByName(String name);

    ChatUser updateChatUser(Long id, ChatUser chatUser);

    boolean deleteChatUser(Long id);
}
