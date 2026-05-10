package com.example.project_4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project_4.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}