package com.newbiebe.chatty;

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
        assertEquals("Effective Java", found.get().getName());
    }

//    @Test
//    public void whenSaved_thenFindsAll() {
//        Book book1 = new Book("Effective Java", "Joshua Bloch");
//        Book book2 = new Book("Clean Code", "Robert C. Martin");
//        bookRepository.save(book1);
//        bookRepository.save(book2);
//
//        List<Book> books = bookRepository.findAll();
//        assertEquals(2, books.size());
//    }
//
//    @Test
//    public void whenInvalidId_thenNotFound() {
//        Optional<Book> found = bookRepository.findById(-99L);
//        assertFalse(found.isPresent());
//    }
//
//    @Test
//    public void whenDeleted_thenDoesNotExist() {
//        Book book = new Book("Effective Java", "Joshua Bloch");
//        book = bookRepository.save(book);
//        Long bookId = book.getId();
//
//        bookRepository.deleteById(bookId);
//        Optional<Book> deleted = bookRepository.findById(bookId);
//        assertFalse(deleted.isPresent());
//    }
}

