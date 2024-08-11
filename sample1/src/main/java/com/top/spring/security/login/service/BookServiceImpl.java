package com.top.spring.security.login.service;
import com.top.spring.security.login.models.Book;
import com.top.spring.security.login.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        if (bookRepository.existsByTitle(book.getTitle())) {
            throw new RuntimeException("Error: Title has been taken.");
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        return bookRepository.save(book);
    }

    @Override
    public Object updateBook(Long id, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) {
            throw new RuntimeException("Error: Book is not found.");
        }
        if (bookRepository.existsByTitleAndIdNot(book.getTitle(), id)) {
            throw new RuntimeException("Error: title is already taken.");
        }

        // Update the Patron details
        Book existingBook = optionalBook.get();
        existingBook.setTitle(book.getTitle());
        existingBook.setPublicationYear(book.getPublicationYear());
        existingBook.setNumber(book.getNumber());
        // Update other fields as needed...

        // Save the updated patron
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

