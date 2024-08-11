package com.top.spring.security.login.service;

import com.top.spring.security.login.models.Patron;

import java.util.List;
import java.util.Optional;

public interface PatronService {
    List<Patron> findAll();

    Optional findById(Long id);

    Patron save(Patron Patron);
    Object update(Long id , Patron Patron);
    void delete(Long id);
}
