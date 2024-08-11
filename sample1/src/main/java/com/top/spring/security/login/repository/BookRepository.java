package com.top.spring.security.login.repository;

import com.top.spring.security.login.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//import org.springframework.stereotype.Repository;

public interface BookRepository extends JpaRepository<Book, Long>  {
    Optional<Book> findById(Long id);

    Boolean existsByTitle(String title);
    Boolean existsByTitleAndIdNot(String title , Long id);
}
