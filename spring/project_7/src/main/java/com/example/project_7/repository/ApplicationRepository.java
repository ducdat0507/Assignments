package com.example.project_7.repository;

import com.example.project_7.model.Application;
import com.example.project_7.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJob(Job job);
}
