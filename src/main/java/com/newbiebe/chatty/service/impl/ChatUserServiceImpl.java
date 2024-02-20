package com.newbiebe.chatty.service.impl;

import com.newbiebe.chatty.entity.ChatUser;
import com.newbiebe.chatty.service.ChatUserService;
import com.newbiebe.chatty.repository.ChatUserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ChatUserServiceImpl implements ChatUserService {
    private final ChatUserRepository chatUserRepository;

    public ChatUserServiceImpl(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    @Override
    public ChatUser saveChatUser(ChatUser chatUser) {
        return chatUserRepository.save(chatUser);
    }

    ;

    @Override
    public List<ChatUser> getAllChatUsers() {
        return chatUserRepository.findAll();
    }

    ;

    @Override
    public ChatUser getChatUserById(Long id) {
        Optional<ChatUser> optionalChatUser = chatUserRepository.findById(id);

        return optionalChatUser.orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    ;

    @Override
    ChatUser updateChatUser(Long id, ChatUser chatUser) {
    }

    ;

//    @Override
//    void deleteChatUser(Long id) {
//    }
//
//    ;
}
