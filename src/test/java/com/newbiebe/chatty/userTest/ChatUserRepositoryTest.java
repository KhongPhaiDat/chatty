package com.newbiebe.chatty.userTest;

import com.newbiebe.chatty.entity.ChatUser;
import com.newbiebe.chatty.repository.ChatUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ChatUserRepositoryTest {

    @Autowired
    private ChatUserRepository chatUserRepository;

    @Test
    public void whenSaved_thenFindsById() {
        ChatUser chatUser = new ChatUser("Joshua Bloch");
        chatUser = chatUserRepository.save(chatUser);

        Optional<ChatUser> found = chatUserRepository.findById(chatUser.getUserId());
        assertTrue(found.isPresent());
        assertEquals("Joshua Bloch", found.get().getName());
    }

    @Test
    public void whenSaved_thenFindsAll() {
        ChatUser book1 = new ChatUser("Joshua Bloch");
        ChatUser book2 = new ChatUser("Robert C. Martin");
        chatUserRepository.save(book1);
        chatUserRepository.save(book2);

        List<ChatUser> chatUsers = chatUserRepository.findAll();
        assertEquals(2, chatUsers.size());
    }

    @Test
    public void whenInvalidId_thenNotFound() {
        Optional<ChatUser> found = chatUserRepository.findById(-99L);
        assertFalse(found.isPresent());
    }

    @Test
    public void whenDeleted_thenDoesNotExist() {
        ChatUser chatUser = new ChatUser("Joshua Bloch");
        chatUser = chatUserRepository.save(chatUser);
        Long chatUserId = chatUser.getUserId();

        chatUserRepository.deleteById(chatUserId);
        Optional<ChatUser> deleted = chatUserRepository.findById(chatUserId);
        assertFalse(deleted.isPresent());
    }
}

