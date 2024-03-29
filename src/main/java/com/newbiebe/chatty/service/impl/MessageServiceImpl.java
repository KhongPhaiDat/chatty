package com.newbiebe.chatty.service.impl;

import com.newbiebe.chatty.entity.Message;
import com.newbiebe.chatty.repository.MessageRepository;
import com.newbiebe.chatty.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }


    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }


    @Override
    public List<Object> getAllMesagesWithUserName() {
        return messageRepository.getAllMessageWithUserName();
    }

    @Override
    public Message getMessageById(Long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);

        return optionalMessage.orElseThrow(() -> new NoSuchElementException("Message not found"));
    }


    @Override
    public List<Message> getMessageByUserId(Long userId) {
        List<Message> messages = messageRepository.findByUserId(userId);
        if (messages.size() == 0) {
            throw new NoSuchElementException("Message not found with user: " + userId);
        }
        return messages;
    }

    @Override
    public Message updateMessage(Long id, Message message) {
        getMessageById(id);
        message.setMessageId(id);
        return saveMessage(message);
    }


    @Override
    public boolean deleteMessage(Long id) {
        messageRepository.deleteById(id);
        Optional<Message> optionalMessage = messageRepository.findById(id);

        return optionalMessage.isEmpty();

    }


}
