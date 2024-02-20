package com.newbiebe.chatty.repository;

import com.newbiebe.chatty.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}