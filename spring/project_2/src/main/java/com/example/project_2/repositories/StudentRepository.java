package com.example.project_2.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.example.project_2.entities.Student;

@Repository
public class StudentRepository {

    private List<Student> students = new ArrayList<>(List.of(
        new Student(1L, "A", "A", List.of(9.0f, 8.0f, 6.0f, 7.5f)),
        new Student(2L, "B", "A", List.of(7.8f, 8.6f, 3.8f, 9.3f)),
        new Student(3L, "C", "A", List.of(6.8f, 7.2f, 5.4f, 3.9f))
    ));

    public List<Student> getAll() {
        return List.copyOf(students);
    }

    public Student findByName(String name) {
        return students.stream().filter(s -> Objects.equals(s.getFullName(), name)).findFirst().orElse(null);
    }

    public void addNew(Student student) {
        students.add(student);
    }
}
