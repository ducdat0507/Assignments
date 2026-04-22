/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project_12.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author ducdat0507
 */
public class PersistenceManager {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.example_project_12_war_1.0-SNAPSHOTPU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
