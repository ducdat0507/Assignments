/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_12.dao;

import java.util.List;

import com.example.project_12.model.Exam;
import com.example.project_12.utils.PersistenceManager;

import jakarta.persistence.EntityManager;

/**
 *
 * @author ducdat0507
 */
public class ExamDao {
    public static List<Exam> getAll() {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        return em.createNamedQuery("Exam.findAll", Exam.class).getResultList();
    }
}
