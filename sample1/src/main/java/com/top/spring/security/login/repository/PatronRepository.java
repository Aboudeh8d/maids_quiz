package com.top.spring.security.login.repository;

import com.top.spring.security.login.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    Boolean existsByIdentity(String identity);
    Boolean existsByName(String name);
    Boolean existsByIdentityAndIdNot(String identity, Long id);
    Boolean existsByNameAndIdNot(String name, Long id);

}
