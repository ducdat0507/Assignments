package com.example.project_6.dao;

import java.util.List;
import java.util.Optional;

import com.example.project_6.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ValidationException;

public class UserDao {
    @PersistenceUnit(name = "persistence-unit")
    private EntityManagerFactory emf;

    public Optional<User> logIn(String username, String password) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery(
                "SELECT * FROM users u WHERE username = :username AND password = :password", 
                User.class
            )
            .setParameter("username", username)
            .setParameter("password", password);

        return Optional.of(query.getSingleResultOrNull());
    }

    public boolean signUp(User user) throws ValidationException {
        EntityManager em = emf.createEntityManager();
        em.persist(user);
        return true;
    }
}
