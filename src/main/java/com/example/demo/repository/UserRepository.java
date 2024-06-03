package com.example.demo.repository;
import com.example.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// UserRepository.java
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

}

