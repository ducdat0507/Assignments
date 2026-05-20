package com.example.project_7.repository;

import com.example.project_7.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
