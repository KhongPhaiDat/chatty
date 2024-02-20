package com.newbiebe.chatty.controller;

import com.newbiebe.chatty.entity.ChatUser;
import com.newbiebe.chatty.service.ChatUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatUsers")
public class ChatUserController {

    private final ChatUserService chatUserService;

    public ChatUserController(ChatUserService chatUserService) {
        this.chatUserService = chatUserService;
    }

    @PostMapping
    public ResponseEntity<ChatUser> createChatUser(@RequestBody ChatUser chatUser) {
        return ResponseEntity.ok(chatUserService.saveChatUser(chatUser));
    }

    @GetMapping
    public List<ChatUser> getAllChatUsers() {
        return chatUserService.getAllChatUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatUser> getChatUserById(@PathVariable Long id) {
        return ResponseEntity.ok(chatUserService.getChatUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatUser> updateChatUser(@PathVariable Long id, @RequestBody ChatUser chatUser) {
        return ResponseEntity.ok(chatUserService.updateChatUser(id, chatUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteChatUser(@PathVariable Long id) {
        return ResponseEntity.ok(chatUserService.deleteChatUser(id));
    }
}