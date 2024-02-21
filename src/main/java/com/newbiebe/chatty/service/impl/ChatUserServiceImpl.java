package com.newbiebe.chatty.service.impl;

import com.newbiebe.chatty.entity.ChatUser;
import com.newbiebe.chatty.service.ChatUserService;
import com.newbiebe.chatty.repository.ChatUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
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
    public ChatUser getChatUserByName(String name) {
        ChatUser chatUser = chatUserRepository.findByName(name);
        if (chatUser == null) {
            throw new NoSuchElementException("User not found with name: " + name);
        }
        return chatUser;
    }

    @Override
    public ChatUser updateChatUser(Long id, ChatUser chatUser) {
        getChatUserById(id);
        chatUser.setUserId(id);
        return saveChatUser(chatUser);
    }

    ;

    @Override
    public boolean deleteChatUser(Long id) {
        chatUserRepository.deleteById(id);
        Optional<ChatUser> optionalChatUser = chatUserRepository.findById(id);

        return optionalChatUser.isEmpty();

    }

    ;
}
