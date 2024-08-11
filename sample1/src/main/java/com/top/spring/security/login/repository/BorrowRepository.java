package com.top.spring.security.login.repository;

import com.top.spring.security.login.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Optional<Borrow> findById(Long id);
    List<Borrow> findByPatronId(Long patronId);
    List<Borrow> findByBookId(Long bookId);
}
