package com.top.spring.security.login.service;

import com.top.spring.security.login.models.Borrow;
import com.top.spring.security.login.payload.request.CreateBorrowRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BorrowService {
    Optional<Borrow> findById(Long id);
    List<Borrow> findAll();

    Borrow save(CreateBorrowRequest borrow);
    Borrow update(Long id , Borrow borrow);
    void delete(Long id);

    List<Borrow>    findByBookId(Long bookId);
    List<Borrow>    findByPatronId(Long patronId);
}
