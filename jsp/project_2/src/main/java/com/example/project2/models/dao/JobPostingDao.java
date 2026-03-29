package com.example.project2.models.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JobPostingDao {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql-persistence-unit");
}
