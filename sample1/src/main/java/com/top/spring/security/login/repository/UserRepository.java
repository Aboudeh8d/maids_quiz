package com.top.spring.security.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.top.spring.security.login.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);


  Boolean existsByUsernameAndIdNot(String username, Long id);

  Boolean existsByEmailAndIdNot(String email, Long id);


}
