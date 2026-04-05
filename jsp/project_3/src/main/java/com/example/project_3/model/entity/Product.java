package com.example.project_3.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;
    
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
}
