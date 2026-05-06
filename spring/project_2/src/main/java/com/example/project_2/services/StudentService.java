package com.example.project_2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.project_2.entities.Student;
import com.example.project_2.repositories.StudentRepository;

@Service
public class StudentService {

    private StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAll() {
        return repository.getAll();
    }

    public Student findByName(String name) {
        return repository.findByName(name);
    }

    public void addNew(Student student) {
        repository.addNew(student);
    }
}
