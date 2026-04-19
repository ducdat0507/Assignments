package com.example.project_9.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 127)
    private String name;

    @Column(name = "price")
    private long price;

    @Column(name = "warranty_status", length = 127)
    private String warranty_status;

    @Column(name = "accessories", length = 512)
    private String accessories;

    @Column(name = "image_url", length = 512)
    private String imageUrl;
}
