package com.newbiebe.chatty;

import com.newbiebe.chatty.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void whenSaved_thenFindsById() {
        Message message = new Message("Hello everyone", 223422982374L, 2L);
        message = messageRepository.save(message);

        Optional<Message> found = messageRepository.findById(message.getMessageId());
        assertTrue(found.isPresent());
        assertEquals("Hello everyone", found.get().getMessage());
        assertEquals(223422982374L, found.get().getTimestamp());
        assertEquals(2L, found.get().getUserId());

    }

    @Test
    public void whenSaved_thenFindsAll() {
        Message message1 = new Message("Hello everyone", 223422982374L, 2L);
        Message message2 = new Message("Hello everyone 2", 223422982375L, 3L);
        messageRepository.save(message1);
        messageRepository.save(message2);

        List<Message> messages = messageRepository.findAll();
        assertEquals(2, messages.size());
    }

    @Test
    public void whenInvalidId_thenNotFound() {
        Optional<Message> found = messageRepository.findById(-99L);
        assertFalse(found.isPresent());
    }

    @Test
    public void whenDeleted_thenDoesNotExist() {
        Message message = new Message("Hello everyone", 223422982374L, 2L);
        message = messageRepository.save(message);
        Long messageId = message.getUserId();

        messageRepository.deleteById(messageId);
        Optional<Message> deleted = messageRepository.findById(messageId);
        assertFalse(deleted.isPresent());
    }
}

