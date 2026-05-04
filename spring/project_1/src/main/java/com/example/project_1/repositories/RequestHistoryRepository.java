package com.example.project_1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project_1.entities.RequestHistory;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long>{
    
}