package com.top.spring.security.login.DTO;
import com.top.spring.security.login.models.Book;
import com.top.spring.security.login.models.Patron;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class BorrowDTO {
    @NotBlank(message = "Borrow Date is mandatory")
    private LocalDate borrowDate;
    @NotBlank(message = "Return Date is mandatory")
    private LocalDate returnDate;


    @NotBlank(message = "Patron is mandatory")
    private Patron patron;
    @NotBlank(message = "Book Date is mandatory")
    private Book book;


    // Getters and setters
    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Book getBook() {
        return book ;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
