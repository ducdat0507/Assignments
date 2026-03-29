package com.example.project2.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Table("applicant")
@Entity
public class Applicant {
    
    @Column("id") 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column("job_id", nullable = false)
    @ForeignKey
    private int jobId;

    @Column("name", nullable = false, length = 100)
    @Size(min = 3)
    private String name;

    @Column("email", nullable = false, length = 100)
    @Email
    private String email;

    @Column("resume_link", nullable = false, length = 255)
    private String resumeLink;


    public int getId() {
        return id;
    }

    public int getJobId() {
        return jobId;
    }
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    @OneToMany()
    @JoinColumn("job_id")
    public JobPosting getJob() {
        return jobId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getResumeLink() {
        return resumeLink;
    }
    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }
}
