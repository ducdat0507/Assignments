package com.example.project2.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Table("job_posting")
@Entity
public class JobPosting {
    
    @Column("id") 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column("title", nullable = false, length = 100)
    @Size(min = 5)
    private String title; 

    @Column("company", nullable = false, length = 100)
    private String company; 

    @Column("location", nullable = false, length = 100)
    private String location; 

    @Column("date_posted", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datePosted; 


    public JobPosting() {

    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

}