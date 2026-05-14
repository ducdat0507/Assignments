package com.example.project_6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project_6.models.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
}