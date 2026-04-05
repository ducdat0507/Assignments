package com.example.project_3.model.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CategoryDao {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
    
}
