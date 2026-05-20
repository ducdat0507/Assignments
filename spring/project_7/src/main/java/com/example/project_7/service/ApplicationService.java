package com.example.project_7.service;

import com.example.project_7.model.Application;
import com.example.project_7.model.Job;
import com.example.project_7.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> findByJob(Job job) {
        return applicationRepository.findByJob(job);
    }

    public Optional<Application> findById(Long id) {
        return applicationRepository.findById(id);
    }

    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }
}
