package com.newbiebe.chatty;

import com.newbiebe.chatty.entity.User;
import com.newbiebe.chatty.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSaved_thenFindsById() {
        User user = new User("Joshua Bloch");
        user = userRepository.save(user);

        Optional<User> found = userRepository.findById(user.getUserId());
        assertTrue(found.isPresent());
        assertEquals("Joshua Bloch", found.get().getName());
    }

//    @Test
//    public void whenSaved_thenFindsAll() {
//        User user1 = new User("Joshua Bloch");
//        User user2 = new User("Robert C. Martin");
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        List<User> books = bookRepository.findAll();
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

