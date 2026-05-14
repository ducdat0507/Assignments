package com.example.project_6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project_6.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}