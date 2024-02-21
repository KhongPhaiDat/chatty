package com.newbiebe.chatty.controller;

import com.newbiebe.chatty.dto.ResponseWrapper;
import com.newbiebe.chatty.entity.ChatUser;
import com.newbiebe.chatty.service.ChatUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/byName")
    public ResponseEntity<ResponseWrapper> getChatUserByName(@RequestParam String name) {
        try {
            ChatUser userData = chatUserService.getChatUserByName(name);

            ResponseWrapper response = new ResponseWrapper("success", userData);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ResponseWrapper response = new ResponseWrapper("error", "User not found");
            return ResponseEntity.status(404).body(response);
        }
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