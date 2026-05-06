package com.example.project_3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "roll_no")
    private String rollNo;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "class_name")
    private String className;
}