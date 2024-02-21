package com.newbiebe.chatty.controller;

import com.newbiebe.chatty.dto.ResponseWrapper;
import com.newbiebe.chatty.entity.Message;
import com.newbiebe.chatty.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.saveMessage(message));
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @GetMapping("/byUserId")
    public ResponseEntity<ResponseWrapper> getMessageByName(@RequestParam Long userId) {
        try {
            Message messageData = messageService.getMessageByUserId(userId);

            ResponseWrapper response = new ResponseWrapper("success", messageData);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ResponseWrapper response = new ResponseWrapper("error", "Message not found");
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message) {
        return ResponseEntity.ok(messageService.updateMessage(id, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMessage(@PathVariable Long id) {
        return ResponseEntity.ok(messageService.deleteMessage(id));
    }
}