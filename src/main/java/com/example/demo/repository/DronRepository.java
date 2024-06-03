package com.example.demo.repository;

import com.example.demo.models.Dron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DronRepository extends JpaRepository<Dron, Long> {
    List<Dron> findByStatus(String status); // Для поиска свободных дронов
}
