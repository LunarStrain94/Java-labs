package com.example.demo.repositories;

import com.example.demo.models.Device;
import com.example.demo.models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    History findById(int id);
}