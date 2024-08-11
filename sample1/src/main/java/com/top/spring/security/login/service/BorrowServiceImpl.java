package com.top.spring.security.login.service;
import com.top.spring.security.login.models.Book;
import com.top.spring.security.login.models.Patron;
import com.top.spring.security.login.payload.request.CreateBorrowRequest;
import com.top.spring.security.login.models.Borrow;
import com.top.spring.security.login.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.top.spring.security.login.repository.PatronRepository;
import com.top.spring.security.login.repository.BookRepository;

import java.time.LocalDate;

@Service
public class BorrowServiceImpl implements  BorrowService{
    @Autowired
    private BorrowRepository borrowRepository;

    public List<Borrow> findAll() {
        return borrowRepository.findAll();
    }

    public Optional<Borrow> findById(Long id) {
        return borrowRepository.findById(id);
    }

    public List<Borrow> findByPatronId(Long patronId) {
        return borrowRepository.findByPatronId(patronId);
    }

    public List<Borrow> findByBookId(Long bookId) {
        return borrowRepository.findByBookId(bookId);
    }


    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private BookRepository bookRepository;

    public Borrow save(CreateBorrowRequest borrow) {
        System.out.println(borrow.getBorrowDate());
        System.out.println(borrow.getReturnDate());
        System.out.println(borrow.getBook().getId());
        System.out.println(borrow.getPatron().getId());
        // Retrieve the Patron by ID
//        Patron patron = patronRepository.findById(patronId)
//                .orElseThrow(() -> new RuntimeException("Patron not found with id: " + patronId));
//
//        // Retrieve the Book by ID
//        Book book = bookRepository.findById(bookId)
//                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
//
//        // Create the Borrow entity
        Patron patron = borrow.getPatron();
        Book book = borrow.getBook();
        LocalDate returnDate = borrow.getReturnDate();
        LocalDate borrowDate = borrow.getBorrowDate();
        Borrow save_borrow = new Borrow(patron, book, borrowDate, returnDate);
        System.out.println("save_borrow");
        System.out.println(save_borrow.getBorrowDate());
        System.out.println(save_borrow.getReturnDate());
        System.out.println(save_borrow.getBook().getId());
        System.out.println(save_borrow.getPatron().getId());
        // Save the Borrow entity
        return borrowRepository.save(save_borrow);
    }


    public Borrow update(Long id, Borrow borrow) {
        Optional<Borrow> optionalBorrow = borrowRepository.findById(id);
        if (!optionalBorrow.isPresent()) {
            throw new RuntimeException("Error: Patron is not found.");
        }
//        if (borrowRepository.existsByIdentityAndIdNot(Borrow.getIdentity(), id)) {
//            throw new RuntimeException("Error: identity is already taken.");
//        }
//
//        if (borrowRepository.existsByNameAndIdNot(Borrow.getName(), id)) {
//            throw new RuntimeException("Error: name is already taken.");
//        }

        // Update the Borrow details
        Borrow existingBorrow = optionalBorrow.get();
        existingBorrow.setBook(borrow.getBook());
        existingBorrow.setPatron(borrow.getPatron());
        existingBorrow.setBorrowDate(borrow.getBorrowDate());
        existingBorrow.setReturnDate(borrow.getReturnDate());
        // Update other fields as needed...

        // Save the updated borrow
        return borrowRepository.save(existingBorrow);
    }


    public void delete(Long id) {
        borrowRepository.deleteById(id);
    }
}
