package com.top.spring.security.login.service;

import com.top.spring.security.login.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional getBookById(Long id);

    Book saveBook(Book book);
    Object updateBook(Long id , Book book);
    void deleteBook(Long id);
}
